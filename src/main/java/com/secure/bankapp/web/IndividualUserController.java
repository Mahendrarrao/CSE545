package com.secure.bankapp.web;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Appointment;
import com.secure.bankapp.model.Request;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.TransferForm;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.RequestRepository;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.service.AccountService;
import com.secure.bankapp.service.EmployeeService;
import com.secure.bankapp.service.TransactionService;
import com.secure.bankapp.service.UserService;
import com.secure.bankapp.util.Constants;
import com.secure.bankapp.validation.UserValidator;

@Controller
public class IndividualUserController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	private Pattern pattern;
	
	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String home(Model model) {
	

		return "home";
	}
	@RequestMapping(value = "/user/help", method = RequestMethod.GET)
	public String help(Model model) {
	
model.addAttribute("appointment", new Appointment());
		return "help";
	}
	
	@RequestMapping(value = "/user/help", method = RequestMethod.POST)
	public String helpPost(@ModelAttribute("appointment") Appointment appointment, Model model) {
		
		if(UserValidator.isBlankString(appointment.getDate()) || UserValidator.isBlankString(appointment.getTime())) {
			model.addAttribute("statusmsg", "Please enter valid input");
			return "help";	
		}
		
		if(appointment.getDate().contains("/") || appointment.getTime().contains("/"))
		{
			model.addAttribute("statusmsg", "Please enter valid input");
			return "help";	
		}
	
		Request request = new Request();

		

		

		request.setAdate(appointment.getDate() + ", " + appointment.getTime());
		request.setRequestDate(Date.valueOf(LocalDate.now()));
		request.setRequestType(Constants.REQUEST_TYPE.APPOINTMENT.toString());
		request.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		request.setStatus(Constants.REQUEST_STATUS.PENDING_APPROVAL.toString());
		requestRepository.save(request);
		model.addAttribute("statusmsg", "You will recieve confirmation  mail post approval");
		return "home";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String add(Model model) {
		
		Request request = new Request();
		request.setRequestDate(Date.valueOf(LocalDate.now()));
		request.setRequestType(Constants.REQUEST_TYPE.NEWACCOUNT.toString());
		request.setUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		request.setStatus(Constants.REQUEST_STATUS.PENDING_APPROVAL.toString());
		requestRepository.save(request);
		model.addAttribute("statusmsg", "Account request submitted. Pending bank approval");
		

		return "home";
	}
	
	@RequestMapping(value = "/user/accounts", method = RequestMethod.GET)
	public String accounts(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		model.addAttribute("accountsList", getAccountList(authentication.getName()));
		model.addAttribute("account", new Account());
		return "viewAccounts";
	}
	
	@RequestMapping(value = "/user/accounts", method = RequestMethod.POST)
	public ModelAndView setDefault(@ModelAttribute("account") Account account, @RequestParam("submit") String submit, Model model) {
		ModelAndView modelAndView = new ModelAndView("viewAccounts");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (account.getAccountId() == null) {
			modelAndView.addObject("accountsList", getAccountList(authentication.getName()));
			modelAndView.addObject("statusmsg", "Please select account");
			return modelAndView;
		}
		Optional<Account> acc= accountService.getAccountById(account.getAccountId());
		if (!acc.isPresent() || !acc.get().getUserId().equals(authentication.getName())) {
			modelAndView.addObject("accountsList", getAccountList(authentication.getName()));
			modelAndView.addObject("statusmsg", "Something went wrong . Please try again");
			return modelAndView;
		}
		Account acc1 = acc.get();	
		if(submit.equals("Make as  Default Account")) {
	
	accountService.setDefaultAccount(acc1);
	model.addAttribute("statusmsg", "Account set successfully");
	modelAndView.addObject("accountsList", getAccountList(authentication.getName()));
	return modelAndView;
		}else if (submit.equals("View Account")) {
			Account info = new Account();
			info.setAccountStatus(acc1.getAccountStatus());
			info.setBalance(acc1.getBalance());
			modelAndView.addObject("info", info);
			modelAndView.addObject("accountsList", getAccountList(authentication.getName()));
			
			return modelAndView;
		} else if (submit.equals("Generate Statement")) {
			
			return postDownloadStatement(model,acc1);			
		}else if (submit.equals("View Account")) {
			UserDetail user = UserDetailRepository.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
			List<Transaction> transactions = transactionService.getTransactionsByAccountId(account.getAccountId());
			Collections.sort(transactions);
			model.addAttribute("user", user);
			model.addAttribute("account", account);
			model.addAttribute("transactions", transactions);
		} 

	
		modelAndView.addObject("statusmsg", "Account set successfully");
		return modelAndView;
	}
	 public List<Account> getAccountList(String userId) {
	      
		   
		   List<Account> accountsView = new ArrayList<Account>();
		   List<Account> accounts = accountService.getAccountsByUserId(userId);
		   for(Account acc: accounts) {
			   
			   Account acc1 = new Account();
			   acc1.setAccountId(acc.getAccountId());
			   accountsView.add(acc1);
			   
		   }
		   return accountsView;
		
		   
	   }
	 
	 @RequestMapping(value = "/user/transfer", method = RequestMethod.GET)
		public String transfer(Model model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			model.addAttribute("accountsList", getAccountList(authentication.getName()));
			model.addAttribute("transfer", new TransferForm());
			return "transfer";
		}
	 
	 @RequestMapping(value = "/user/transfer", method = RequestMethod.POST)
		public String transferPost(@ModelAttribute("transfer") TransferForm form, @RequestParam("submit") String submit, Model model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			Optional<Account> acc  = accountService.getAccountById(form.getAccountId());
			if(!acc.isPresent() || !acc.get().getUserId().equals(authentication.getName())) {
				model.addAttribute("statusmsg", "Something went wrong . Please try again");
				return "transfer";
			}
			
		
			Account fromAccount = acc.get();
			if (form.getAmount() == null || form.getAmount() < 0)  {
				model.addAttribute("statusmsg", "Invalid input entered");
				return "transfer";
			}
			
			if (fromAccount.getBalance() - form.getAmount() < 0) {
				model.addAttribute("statusmsg", "Insufficient balance. Cant Transfer");
				return "transfer";
			}
			
			if (submit.equals("Transfer by Account")) {
				try {
					Long toAccount = Long.parseLong(form.getToAccount());
					if (!accountService.getAccountById(toAccount).isPresent()) {
						model.addAttribute("statusmsg", "Recipient account not found");
						return "transfer";
					}
					
					accountService.transferFunds(accountService.getAccountById(toAccount).get(), fromAccount, form.getAmount());
					model.addAttribute("statusmsg", "Transaction Successfull. It is sent to Bank Approval");
					return "transfer";
				} catch(NumberFormatException e) {
					model.addAttribute("statusmsg", "Enter valid Account details");
					return "transfer";
				}
				
			} else if (submit.equals("Transfer by Email")) {
				if (!pattern.matches(UserValidator.EMAIL_PATTERN, form.getToAccount())) {
					model.addAttribute("statusmsg", "Enter valid email address");
					return "transfer";
				}
				
			Optional<Account> toAccount =	accountService.getAccountByEmail(form.getToAccount());
			  if (!toAccount.isPresent()) { 
				  model.addAttribute("statusmsg",
			  "Recipient account not found");
				  return "transfer"; }
			  
				
				accountService.transferFunds(toAccount.get(), fromAccount, form.getAmount());
				model.addAttribute("statusmsg", "Transaction Successfull. It is sent to Bank Approval");
				return "transfer";
			 
				
			} else if (submit.equals("Transfer by Phone")) {
				if (!pattern.matches(UserValidator.PHONE_PATTERN, form.getToAccount())) {
					model.addAttribute("statusmsg", "Enter valid phone no.");
					return "transfer";
				}
				Optional<Account> toAccount =	accountService.getAccountByPhone(form.getToAccount());
				  if (!toAccount.isPresent()) { 
					  model.addAttribute("statusmsg",
				  "Recipient account not found");
					  return "transfer"; }
				  
				  accountService.transferFunds(toAccount.get(), fromAccount, form.getAmount());
					model.addAttribute("statusmsg", "Transaction Successfull. It is sent to Bank Approval");
					return "transfer";
			}
			
			
			
			return "transfer";
		}

	
		public ModelAndView postDownloadStatement(Model model, Account account) {
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserDetail user = UserDetailRepository.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		

			List<Transaction> transactions = transactionService.getTransactionsByAccountId(account.getAccountId());
			
			model.addAttribute("user", user);
			model.addAttribute("title", "Account Statements");
			model.addAttribute("user", user);
			model.addAttribute("account", account);
			model.addAttribute("transactions", transactions);
	

			return new ModelAndView("pdfView", "model", model);
		}
	
	
}