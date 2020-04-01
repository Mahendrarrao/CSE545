package com.secure.bankapp.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.Search;
import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.service.AccountService;
import com.secure.bankapp.service.EmployeeService;
import com.secure.bankapp.service.SystemLogService;
import com.secure.bankapp.service.TransactionService;
import com.secure.bankapp.service.UserService;
import com.secure.bankapp.validation.SearchValidator;
import com.secure.bankapp.validation.UserValidator;

@Controller
public class AdministratorController {
	@Autowired
	private SystemLogService logService;

	//Begin Mappings for Administrators
	//Functions addressing functionality described in Course Project Requirements Document namely,
	//
	//can view, create, modify, and delete employees’account.
	//can authorize or decline employees’request.
	//can access the system log file. (System log file is only accessible to the administrator)
	//
	@Autowired
	private SearchValidator searchValidator;
	
	@Autowired
	private AdministratorController administratorController;

	@Autowired
	private AccountService accountService;
	

	@Autowired
	private UserDetailRepository UserDetailRepository;
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;

	@RequestMapping(value = "/admin/logs", method = RequestMethod.GET)
	public String getLogDetails( Model model) {
		List<SystemLog> test= logService.getAllLogs();
		
		System.out.println(test);
		int totalRecords = test.size();
		Collections.sort(test);
		Collections.reverse(test);
		model.addAttribute("logList", test);
		model.addAttribute("logLength", test.size());
		model.addAttribute("pages", ((totalRecords/10)+1));
		return "viewsystemlogs";
	}

	
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String home(Model model) {	
		model.addAttribute("delete", new Search());
		model.addAttribute("search", new Search());
		return "adminhome";
	}
	 @RequestMapping(value = "/admin/add", method = RequestMethod.GET)
	    public String registration(Model model) {
	        model.addAttribute("userForm", new RegistrationForm());
	
	        return "addUserAdmin";
	    }
	 @RequestMapping(value = {"/admin/home" }, method = RequestMethod.POST)
		public String deleteUser( @ModelAttribute("delete") Search search, BindingResult result, Model model) {
		
			searchValidator.validate(search, result);
			if(result.hasErrors()) {
				model.addAttribute("message", "Deletion failed: User not found");
				return administratorController.home(model) ;
			}
			accountService.deleteByUserId(search.getUserName());
			
			model.addAttribute("message", "User deleted successfully");
			return administratorController.home(model);
		}
	 
	 
		@RequestMapping(value = "/admin/user", method = RequestMethod.POST)
		public String view(@ModelAttribute("search") Search option, BindingResult result,  Model model) {				
			UserDetail userDetail = UserDetailRepository.findByUserId(option.getUserName());
			searchValidator.validate(option, result);
			if(result.hasErrors()) {
				model.addAttribute("message", "Deletion failed: User not found");
				return administratorController.home(model) ;
			}
			getAccountList(model, option.getUserName());
			Account account = new Account();
			account.setUserId(option.getUserName());
			model.addAttribute("user", userDetail);
			model.addAttribute("account", account);
			return "modifyAccount";
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
	//Administrators can view employee's accounts.
	//TODO//
	
	
	//Administrators can create employee's accounts.
	//TODO//
	
	
	//Administrators can modify employee's accounts.
	//TODO//
	
	
	//Administrators can delete employee's accounts.
	//TODO//
	
	
	//Administrators can authorize (approve) employee's requests.
	//TODO//
	
	
	//Administrators can decline (reject) employee's requests.
	//TODO//
	
	
	//Administrators can access the system log file. (Only administrators).
	//TODO//
	

}