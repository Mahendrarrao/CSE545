package com.secure.bankapp.web;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.Search;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.model.UserProfile;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.service.AccountService;
import com.secure.bankapp.service.TransactionService;
import com.secure.bankapp.service.UserService;
import com.secure.bankapp.util.Constants;
import com.secure.bankapp.validation.ProfileValidator;
import com.secure.bankapp.validation.SearchValidator;
import com.secure.bankapp.validation.UserValidator;

@Controller
public class Tier1EmployeeController {

	/*
	 * @Autowired private TransactionService transactionService;
	 * 
	 * @Autowired private AccountService accountService;
	 * 
	 * @Autowired private UserDetailRepository UserDetailRepository;
	 * 
	 * //Begin Mappings for Tier 1 Employees //Functions addressing functionality
	 * described in Course Project Requirements Document namely, // //can view,
	 * create and authorize non-critical transactions upon having authorization from
	 * the bank customer and tier 2 employee //can view customers’ accounts //can
	 * issue cashier cheques as well as handle fund transfer //can authorize or
	 * decline customer’s request //
	 * 
	 * //Tier 1 Employees can view non-critical transactions upon having
	 * authorization from the bank customer and tier 2 employee.
	 * 
	 * @RequestMapping(value = "/emp1/getNonCriticalTransactions", method =
	 * RequestMethod.GET) public String getTransactionsEmp1(Model model) { // TODO
	 * // Needs authentication that transactions returned to tier1 employee are all
	 * nonCritical with authentication // from tier2 employee and customer.
	 * model.addAttribute("transactionList",
	 * transactionService.getNonCriticalTransactions());
	 * 
	 * return ""; }
	 * 
	 * 
	 * //Tier 1 Employees can authorize (approve) non-critical transactions upon
	 * having authorization from the bank customer and tier 2 employee.
	 * 
	 * @RequestMapping(value = "/emp1/approveNonCriticalTransactions", method =
	 * RequestMethod.POST) public String
	 * approveTransactionsEmp1(@ModelAttribute("transactions") List<Transaction>
	 * transactions, Model model) { // TODO // Needs authentication that
	 * transactions supplied by tier1 employee are all nonCritical with
	 * authentication // from tier2 employee and customer.
	 * transactionService.approveTransactions(transactions);
	 * 
	 * return ""; }
	 * 
	 * 
	 * //Tier 1 Employees can authorize (reject) non-critical transactions upon
	 * having authorization from the bank customer and tier 2 employee.
	 * 
	 * @RequestMapping(value = "/emp1/rejectNonCriticalTransactions", method =
	 * RequestMethod.POST) public String
	 * rejectTransactionsEmp1(@ModelAttribute("transactions") List<Transaction>
	 * transactions, Model model) { // TODO // Needs authentication that
	 * transactions supplied by tier1 employee are all nonCritical with
	 * authentication // from tier2 employee and customer.
	 * transactionService.rejectTransactions(transactions);
	 * 
	 * return ""; }
	 * 
	 * //Tier 1 Employees can create non-critical transactions upon having
	 * authorization from the bank customer and tier 2 employee. //TODO//
	 * 
	 * 
	 * //Tier 1 Employees can view customers’ accounts. //TODO//
	 * 
	 * 
	 * //Tier 1 Employees can issue cashier checks as well as handle fund transfer.
	 * //TODO//
	 * 
	 * 
	 * //Tier 1 Employees can authorize customer’s request. //TODO//
	 * 
	 * 
	 * //Tier 1 Employees can reject customer’s request. //TODO//
	 * 
	 * 
	 * // //Other functions addressing unknown or non-required functionality PLEASE
	 * COMMENT FUNCTIONS!// //
	 * 
	 * //PLEASE COMMENT FUNCTION//
	 * 
	 * @RequestMapping(value = {"/emp1/get/{userId}","/emp1/get/{userId}" }, method
	 * = RequestMethod.GET) public String getUserAccount(@PathVariable("userId")
	 * String userId, Model model) {
	 * 
	 * UserDetail user = UserDetailRepository.findByUserId(userId); List<Account>
	 * accounts = accountService.getAccountsByUserId(userId); List<Account>
	 * viewAccounts = new ArrayList<>(); for(Account a1 : accounts) { Account a2 =
	 * new Account(); a2.setAccountId(a1.getAccountId());
	 * a2.setAccountType(a1.getAccountType());
	 * a2.setAccountStatus(a1.getAccountStatus()); viewAccounts.add(a2); }
	 * 
	 * 
	 * model.addAttribute("userProfile", new UserDetail(user));
	 * model.addAttribute("accounts", viewAccounts);
	 * 
	 * 
	 * return ""; }
	 * 
	 * 
	 * //PLEASE COMMENT FUNCTION//
	 * 
	 * @RequestMapping(value = {"/emp1/add/" }, method = RequestMethod.POST) public
	 * String addFunds(@ModelAttribute("transaction") Transaction transaction, Model
	 * model) {
	 * 
	 * transaction.setTransactionType("Credit");
	 * accountService.creditFunds(accountService.getAccountById(transaction.
	 * getToAccount()).get(), transaction);
	 * 
	 * return ""; }
	 * 
	 * 
	 * //PLEASE COMMENT FUNCTION//
	 * 
	 * @RequestMapping(value = {"/emp1/withdraw/" }, method = RequestMethod.POST)
	 * public String withdrawFunds(@ModelAttribute("transaction") Transaction
	 * transaction, Model model) {
	 * 
	 * transaction.setTransactionType("Debit"); try {
	 * accountService.debitFunds(accountService.getAccountById(transaction.
	 * getToAccount()).get(), transaction); } catch (InsufficientBalanceException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return ""; }
	 */
	

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Autowired
	private ProfileValidator profileValidator;
	
	@Autowired
	private SearchValidator searchValidator;
	
	 @Autowired
	    private UserService userService;
	

	
	@Autowired
	private Tier1EmployeeController tier1EmployeeController;

	//Begin Mappings for Tier 2 Employees
	//Functions addressing functionality described in Course Project Requirements Document namely,
	//
	//can view, create, modify, and closecustomer’accounts
	//can authorize transactions
	//can initiate modification of accounts
	//
	
	
	//Tier 2 Employees can view customer's accounts.
	//TODO//
	
	
	//Tier 2 Employees can create customer's accounts.
	//TODO//
	

    @Autowired
    private UserValidator userValidator;
	//Tier 2 Employees can modify customer's accounts.
	@RequestMapping(value = {"/emp1/update/" }, method = RequestMethod.POST)
	public String modifyUserAccount(@ModelAttribute("userProfile") UserDetail userProfile, Model model) {

		UserDetail user = UserDetailRepository.findByUserId(userProfile.getUserId());
		user.setAddress(userProfile.getAddress());
		user.setEmail(userProfile.getEmail());
		user.setUpdatedOn(java.sql.Date.valueOf(LocalDate.now()));
		user.setPhone(userProfile.getPhone());
		UserDetailRepository.save(user);
		
		
		return "";
	}
	
	
	//Tier 2 Employees can close customer's accounts.
	@RequestMapping(value = {"/emp1/view" }, method = RequestMethod.POST)
	public String closeUserAccount( @ModelAttribute("account") Account account,@RequestParam String action, Model model) {
		Search s = new Search();
		
		s.setUserName(account.getUserId());
		
		if(account.getAccountId() == null) {
			model.addAttribute("statusmsg", "Please select an Account");
			return tier1EmployeeController.view(s, model);
		}
		if(account.getBalance() == null) {
			model.addAttribute("statusmsg", "Balance cannot be empty");
			return tier1EmployeeController.view(s, model);
		}
		
		
		
		if (action.equals("Deposit")) {
	
			accountService.creditFundsByEmp1(account);
			
		} else 	
			if (action.equals("Withdraw")) {
				Account acc1= accountService.getAccountById(account.getAccountId()).get();
				if(acc1.getBalance() - account.getBalance() < 0) {
					model.addAttribute("statusmsg", "Not sufficient balance");
					return tier1EmployeeController.view(s, model);
				}
				accountService.debitFundsByEmp1(account);
			} else 	
				if (action.equals("Issue Check")) {
					
					if (userValidator.isBlankString(account.getName())) {

						model.addAttribute("statusmsg", "Enter Reciept Name to issue check");
						return tier1EmployeeController.view(s, model);
					}
					Account acc1= accountService.getAccountById(account.getAccountId()).get();
					if(acc1.getBalance() - account.getBalance() < 0) {
						model.addAttribute("statusmsg", "Not sufficient balance");
						return tier1EmployeeController.view(s, model);
					}
					accountService.debitFundsByEmp1(account);
				}
	
		
		model.addAttribute("statusmsg", "Transaction completed successfully");
		return tier1EmployeeController.view(s, model);
	}
	
	
	@RequestMapping(value = {"/emp1/home" }, method = RequestMethod.POST)
	public String deleteUser( @ModelAttribute("delete") Search search, BindingResult result, Model model) {
	
		searchValidator.validate(search, result);
		if(result.hasErrors()) {
			model.addAttribute("message", "User not found");
			return tier1EmployeeController.home(model) ;
		}
		
		
		accountService.deleteByUserId(search.getUserName());
		
		model.addAttribute("message", "User deleted successfully");
		return tier1EmployeeController.home(model);
	}
	
	
	//Tier 2 Employees can authorize (approve) transactions.
	@RequestMapping(value = "/emp1/transactions/action", method = RequestMethod.POST)
	public String approveTransactionsemp1(@ModelAttribute("selectedTransactions") Transaction transaction, @RequestParam String action, Model model) {
		//TODO
		//Need to ensure transactions supplied by tier 2 employee are able to be authorized (approved) by tier 2 employee.
		if (transaction.getTransactions() != null) {
		if (action.equals("Approve"))
		transactionService.approveTransactions(transaction.getTransactions());
		else if(action.equals("Reject"))
			transactionService.rejectTransactions(transaction.getTransactions());
		}

		return "redirect:/emp1/transactions/";
	}
	
	@RequestMapping(value = "/emp1/home", method = RequestMethod.GET)
	public String home(Model model) {	
		model.addAttribute("search", new Search());
		model.addAttribute("delete", new Search());
		return "tier1home";
	}
	

	
	@RequestMapping(value = "/emp1/profile", method = RequestMethod.GET)
	public String profile(Model model) {
		
		UserDetail user = UserDetailRepository.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		UserProfile profile = new UserProfile(user.getEmail(), user.getPhone(), user.getAddress());
		model.addAttribute("user", profile);
		return "emp2profile";
	}
	
	@RequestMapping(value = "/emp1/profile", method = RequestMethod.POST)
	public String profileSave(@ModelAttribute("user") UserProfile profile,BindingResult bindingResult,Model model) {
		  profileValidator.validate(profile, bindingResult);

	        if (bindingResult.hasErrors()) {
	            return "emp1profile";
	        }
		UserDetail user = UserDetailRepository.findByUserId(SecurityContextHolder.getContext().getAuthentication().getName());
		user.setAddress(profile.getAddress());
		user.setEmail(profile.getEmail());
		user.setPhone(profile.getPhone());
		user.setUpdatedOn(Date.valueOf(LocalDate.now()));
		UserDetailRepository.save(user);
		
	
		return "redirect:/emp1/profile";
	}
	 @RequestMapping(value = "/emp1/add", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new RegistrationForm());

	        return "addUser";
	    }

	    @RequestMapping(value = "/emp1/add", method = RequestMethod.POST)
	    public String registration(@Valid @ModelAttribute("userForm") RegistrationForm userForm, BindingResult bindingResult, Model model) {
	       userForm.setPassword("test1234");
	       userForm.setConfirmPassword("test1234");
	    	userValidator.validate(userForm, bindingResult);

	        if (bindingResult.hasErrors()) {
	            return "addUser";
	        }
	        
	      //  userForm.setUserType(userType);
	        UserCred userCred = new UserCred();
	        userCred.setUserId(userForm.getUserId());
	        userCred.setPassword(userForm.getPassword());
	        userCred.setRoleId(Long.parseLong(userForm.getCustomerType()));
	        userCred.setStatus(Constants.PASS_CHANGE);
	        
	        UserDetail userDetail = new UserDetail();
	        userDetail.setFullName(userForm.getFirstName() + " " + userForm.getLastName());
	        userDetail.setUserId(userForm.getUserId());
	        userDetail.setEmail(userForm.getEmail());
	        userDetail.setPhone(userForm.getPhone());
	        userDetail.setCreatedAt(Date.valueOf(LocalDate.now()));
	        userDetail.setUpdatedOn(Date.valueOf(LocalDate.now()));
	        userDetail.setDob(Date.valueOf(LocalDate.now()));
	        userDetail.setGender(userForm.getGender());
	        userDetail.setAddress(userForm.getAddress() + ", " + userForm.getCity());
	        userService.save(userCred, userDetail);
	    	model.addAttribute("message", "User Added successfully");
			return tier1EmployeeController.home(model) ;
	      
	    }
	
	@RequestMapping(value = "/emp1/user", method = RequestMethod.POST)
	public String view(@ModelAttribute("search") Search option,  Model model) {	
		if(UserValidator.isBlankString(option.getUserName())) {
			model.addAttribute("message", "Field should not be empty");
			return tier1EmployeeController.home(model) ;
		}
		
		UserDetail userDetail = UserDetailRepository.findByUserId(option.getUserName());
		UserCred user = userCredentialRepository.findByUserId(option.getUserName());
		
		if(userDetail== null || user.getRoleId() != 0 )
		{
			model.addAttribute("message", "User not found");
			return tier1EmployeeController.home(model) ;
		}
		getAccountList(model, option.getUserName());
		Account account = new Account();
		account.setUserId(option.getUserName());
		model.addAttribute("user", userDetail);
		model.addAttribute("account", account);

		return "viewUser";
	
	}
	
	
	
	@RequestMapping(value = "/emp1/save", method = RequestMethod.POST)
	public String userSave(@ModelAttribute("user") UserDetail profile,BindingResult bindingResult,Model model) {
	
		UserDetail user = UserDetailRepository.findByUserId(profile.getUserId());
		user.setAddress(profile.getAddress());
		user.setEmail(profile.getEmail());
		user.setPhone(profile.getPhone());
		user.setFullName(profile.getFullName());
		user.setGender(profile.getGender());
		user.setUpdatedOn(Date.valueOf(LocalDate.now()));
		UserDetailRepository.save(user);
		
	
		return "redirect:/emp1/home";
	}
	
	
	   public void getAccountList(Model model, String userId) {
	      
	   
		   List<Account> accountsView = new ArrayList<Account>();
		   List<Account> accounts = accountService.getAccountsByUserId(userId);
		   for(Account acc: accounts) {
			   
			   Account acc1 = new Account();
			   acc1.setAccountId(acc.getAccountId());
			   accountsView.add(acc1);
			   
		   }
		   model.addAttribute("accountsList", accountsView);
		   
	   }
	
	
	
	//Tier 2 Employees can authorize (reject) transactions.
	@RequestMapping(value = "/emp1/reject", method = RequestMethod.POST)
	public String rejectTransactionsemp1(@ModelAttribute("selectedTransactions") Transaction transactions, Model model) {
		//TODO
		//Need to ensure transactions supplied by tier 2 employee are able to be authorized (rejected) by tier 2 employee.
	
		transactionService.rejectTransactions(transactions.getTransactions());

		return "";
	}
	
	
	//Tier 2 Employees can can initiate modification of accounts.
	//TODO//NOTE// what types of accounts is not defined in requirements and needs to be resolved.//
	
	
	//
	//Other functions addressing unknown or non-required functionality PLEASE COMMENT FUNCTIONS!//
	//
	
	
	//PLEASE COMMENT FUNCTION//
	@RequestMapping(value = "/emp1/transactions", method = RequestMethod.GET)
	public String getTransactionsemp1( Model model) {
		model.addAttribute("transactionList", transactionService.getNonCriticalTransactions());
		model.addAttribute("selectedTransactions", new Transaction());

		return "noncriticaltransactions";
	}
	
}