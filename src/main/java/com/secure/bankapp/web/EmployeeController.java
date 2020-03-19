package com.secure.bankapp.web;

import java.time.LocalDate;
import java.util.ArrayList;import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.service.AccountService;
import com.secure.bankapp.service.EmployeeService;
import com.secure.bankapp.service.TransactionService;
import com.secure.bankapp.service.UserService;
import com.secure.bankapp.util.Constants;

@Controller
public class EmployeeController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;

	@RequestMapping(value = "/emp2/getTransactions", method = RequestMethod.GET)
	public String getTransactionsEmp2(Model model) {
		model.addAttribute("transactionList", transactionService.getCriticalTransactions());

		return "";
	}

	@RequestMapping(value = "/emp2/approve", method = RequestMethod.POST)
	public String approveTransactionsEmp2(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {

		transactionService.approveTransactions(transactions);

		return "";
	}

	@RequestMapping(value = "/emp2/reject", method = RequestMethod.POST)
	public String rejectTransactionsEmp2(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {

		transactionService.rejectTransactions(transactions);

		return "";
	}

	@RequestMapping(value = "/emp1/getTransactions", method = RequestMethod.GET)
	public String getTransactionsEmp1(Model model) {
		model.addAttribute("transactionList", transactionService.getNonCriticalTransactions());

		return "";
	}

	@RequestMapping(value = "/emp1/approve", method = RequestMethod.POST)
	public String approveTransactionsEmp1(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {

		transactionService.approveTransactions(transactions);

		return "";
	}

	@RequestMapping(value = "/emp1/reject", method = RequestMethod.POST)
	public String rejectTransactionsEmp1(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {

		transactionService.rejectTransactions(transactions);

		return "";
	}
	
	@RequestMapping(value = {"/emp1/get/{userId}","/emp2/get/{userId}" }, method = RequestMethod.GET)
	public String getUserAccount(@PathVariable("userId") String userId, Model model) {

		UserDetail user = UserDetailRepository.findByUserId(userId);
		List<Account> accounts = accountService.getAccountsByUserId(userId);
		List<Account> viewAccounts = new ArrayList<>();
		for(Account a1 : accounts) {
			Account a2 = new Account();
			a2.setAccountId(a1.getAccountId());
			a2.setAccountType(a1.getAccountType());
			a2.setAccountStatus(a1.getAccountStatus());
			viewAccounts.add(a2);
		}
		
		
		model.addAttribute("userProfile", new UserDetail(user));
		model.addAttribute("accounts", viewAccounts);
		

		return "";
	}
	
	@RequestMapping(value = {"/emp2/delete/" }, method = RequestMethod.POST)
	public String deleteUserAccount( Long accountId, Model model) {

	
		Account account = accountService.getAccountById(accountId).get();
		accountService.deleteAccount(account);
		
		
		return "";
	}
	
	@RequestMapping(value = {"/emp2/update/" }, method = RequestMethod.POST)
	public String modifyUserAccount(@ModelAttribute("userProfile") UserDetail userProfile, Model model) {

		UserDetail user = UserDetailRepository.findByUserId(userProfile.getUserId());
		user.setAddress(userProfile.getAddress());
		user.setEmail(userProfile.getEmail());
		user.setUpdatedOn(java.sql.Date.valueOf(LocalDate.now()));
		user.setPhone(userProfile.getPhone());
		UserDetailRepository.save(user);
		
		return "";
	}
	
	@RequestMapping(value = {"/emp1/add/" }, method = RequestMethod.POST)
	public String addFunds(@ModelAttribute("transaction") Transaction transaction, Model model) {

		transaction.setTransactionType("Credit");
		accountService.creditFunds(accountService.getAccountById(transaction.getToAccount()).get(), transaction);
		
		return "";
	}
	
	@RequestMapping(value = {"/emp1/withdraw/" }, method = RequestMethod.POST)
	public String withdrawFunds(@ModelAttribute("transaction") Transaction transaction, Model model) {

		transaction.setTransactionType("Debit");
		try {
			accountService.debitFunds(accountService.getAccountById(transaction.getToAccount()).get(), transaction);
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "";
	}
	
	
	
	
	
	

}
