package com.secure.bankapp.web;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.secure.bankapp.util.Constants;
import com.secure.bankapp.validation.SearchValidator;
import com.secure.bankapp.validation.UserModifyValidator;
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
	private UserValidator userValidator;
	
	 @Autowired
	    private UserService userService;
	 
	 @Autowired
	 private UserModifyValidator userValidator2;
	
	

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
		model.addAttribute("logs", test);
		
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
		RegistrationForm form = new RegistrationForm();
		form.setCustomerType("2");
	        model.addAttribute("userForm",form );
	
	        return "addUserAdmin";
	    }
	 
	 @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
	    public String registrationPOST(@ModelAttribute("userForm") RegistrationForm userForm, BindingResult result,  Model model) {
		 userForm.setPassword("dshah2139d");
	       userForm.setConfirmPassword("dshah2139d");
	        userValidator.validate(userForm, result);
	    	if(result.hasErrors()) {
			
	    		  return "addUserAdmin";
			}
	    	
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
	        return administratorController.home(model);
	    }
	 @RequestMapping(value = {"/admin/delete" }, method = RequestMethod.POST)
		public String deleteUser( @ModelAttribute("delete") Search search, BindingResult result, Model model) {
		
			searchValidator.validate(search, result);
			if(result.hasErrors()) {
				model.addAttribute("message", "User not found");
				return administratorController.home(model) ;
			}
			accountService.deleteByUserId(search.getUserName());
			
			model.addAttribute("message", "User deleted successfully");
			return administratorController.home(model);
		}
	 
	 @RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
		public String home1(Model model) {	
			
			return "redirect:/admin/home";
		}
	 
	 
	 @RequestMapping(value = "/admin/user", method = RequestMethod.POST)
		public String userSave(@ModelAttribute("user") UserDetail profile,BindingResult bindingResult,Model model) {
		
			userValidator2.validate(profile, bindingResult);
			if(bindingResult.hasErrors()) {
				
				Account account = new Account();
				account.setUserId(profile.getUserId());
				model.addAttribute("account", account);	
				return "modifyAccountAdmin";
				
			}
			
			
			
			UserDetail user = UserDetailRepository.findByUserId(profile.getUserId());
			user.setAddress(profile.getAddress());
			user.setEmail(profile.getEmail());
			user.setPhone(profile.getPhone());
			user.setFullName(profile.getFullName());
			user.setGender(profile.getGender());
			user.setUpdatedOn(Date.valueOf(LocalDate.now()));
			UserDetailRepository.save(user);
			
		
			return "redirect:/admin/home";
		}
	 private Pattern pattern;
	 @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
		public String view(@RequestParam("userName") String userName,  Model model) {	
			if(UserValidator.isBlankString(userName)) {
				model.addAttribute("message", "Field should not be empty");
				return administratorController.home(model) ;
			}
			if (!pattern.matches(Constants.PASSWORD_PATTERN, userName)) {

				model.addAttribute("message", "Invalid input");
				SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
				logService.recordLog(log);
				return administratorController.home( model);
			}
			
			UserDetail userDetail = UserDetailRepository.findByUserId(userName);
			UserCred user = userCredentialRepository.findByUserId(userName);
			HashSet<Long> set = new HashSet<>();
			set.add(2L);
			set.add(3L);
			if(userDetail== null || !set.contains(user.getRoleId()) )
			{
				model.addAttribute("message", "User not found");
				return administratorController.home(model) ;
			}
			
		
			model.addAttribute("user", userDetail);
		

			return "modifyAccountAdmin";
		
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