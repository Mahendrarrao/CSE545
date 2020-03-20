package com.secure.bankapp.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.service.AccountService;
import com.secure.bankapp.service.EmployeeService;
import com.secure.bankapp.service.TransactionService;
import com.secure.bankapp.service.UserService;

@Controller
public class AdministratorController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;

	//Begin Mappings for Administrators
	//Functions addressing functionality described in Course Project Requirements Document namely,
	//
	//can view, create, modify, and delete employees’account.
	//can authorize or decline employees’request.
	//can access the system log file. (System log file is only accessible to the administrator)
	//
	
	
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
