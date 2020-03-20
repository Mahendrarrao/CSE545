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
public class IndividualUserController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;

	//Begin Mappings for Individual Users (Individual Customers)
	//Functions addressing functionality described in Course Project Requirements Document namely,
	//
	//can view, debit, credit and transfer money from his/her bank account
	//can initiate modification of personal account
	//can view, authorize and decline customer’s money transfer requests
	//can initiate request for creation of an additional account
	//
	
	
	//Individual Users can view his/her bank account(s) (including checking, debit, credit accounts, if exists).
	//TODO//
	
	
	//Individual Users can submit request to transfer money from his/her debit banking account, if it exists.
	//TODO//
	
	
	//Individual Users can submit request to transfer money from his/her credit banking account, if it exists.
	//TODO//
	
	
	//Individual Users can submit request to withdraw money from his/her checking banking account, if it exists.
	//TODO//
	
	
	//Individual Users can initiate modification of personal account
	//TODO//
	
	
	//Individual Users can view, authorize and decline customer’s money transfer requests.
	//Interpreting this as customer's money transfer requests being request to take money from customer.
	//
	//Individual Users can view customer's money transfer requests.
	//TODO//
	
	
	//Individual Users can authorize (accept) customer's money transfer requests.
	//TODO//
	
	
	//Individual Users can decline (reject) customer's money transfer requests.
	//TODO//
	
	
	//Individual Users can initiate request for creation of an additional account
	//TODO//
	
	
}
