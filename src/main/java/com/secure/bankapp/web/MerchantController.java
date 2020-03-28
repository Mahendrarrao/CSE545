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
public class MerchantController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;

	//Begin Mappings for Merchants (Organizations)
	//Functions addressing functionality described in Course Project Requirements Document namely,
	//
	//can view, debit, credit and transfer money from their bank accounts with proper authorization.
	//can initiate modification of personal account
	//can view, authorize and decline other customer’s requests
	//
	
	
	//Merchants can view organization's bank account(s) (including various checking, debit, credit accounts, if exists).
	//TODO//
	
	
	//Merchants can transfer money from organization's debit accounts, with proper authorization.
	//TODO//
	
	
	//Merchants can transfer money from organization's credit accounts, with proper authorization.
	//TODO//
	
	
	//Merchants can transfer money from organization's checking accounts, with proper authorization.
	//TODO//
	
	
	//Merchants can initiate modification of personal account
	//TODO//
	
	
	//Merchants can view, authorize and decline other customer's requests.
	//Interpreting requests as including money requests, but could include other requests as well. Need to define, TODO
	//
	//Merchants can view customer's money transfer requests.
	//TODO//
	
	
	//Merchants can authorize (accept) customer's money transfer requests.
	//TODO//
	
	
	//Merchants can decline (reject) customer's money transfer requests.
	//TODO//
	
	
}