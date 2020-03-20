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
import com.secure.bankapp.service.TransactionService;

@Controller
public class Tier2EmployeeController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;

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
	
	
	//Tier 2 Employees can modify customer's accounts.
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
	
	
	//Tier 2 Employees can close customer's accounts.
	@RequestMapping(value = {"/emp2/closeAccount/" }, method = RequestMethod.POST)
	public String closeUserAccount( Long accountId, Model model) {
		
		Account account = accountService.getAccountById(accountId).get();
		//NOTE//Is Delete the same as Close?//
		accountService.deleteAccount(account);
		
		return "";
	}
	
	
	//Tier 2 Employees can authorize (approve) transactions.
	@RequestMapping(value = "/emp2/approve", method = RequestMethod.POST)
	public String approveTransactionsEmp2(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {
		//TODO
		//Need to ensure transactions supplied by tier 2 employee are able to be authorized (approved) by tier 2 employee.
		transactionService.approveTransactions(transactions);

		return "";
	}
	
	
	//Tier 2 Employees can authorize (reject) transactions.
	@RequestMapping(value = "/emp2/reject", method = RequestMethod.POST)
	public String rejectTransactionsEmp2(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {
		//TODO
		//Need to ensure transactions supplied by tier 2 employee are able to be authorized (rejected) by tier 2 employee.
		transactionService.rejectTransactions(transactions);

		return "";
	}
	
	
	//Tier 2 Employees can can initiate modification of accounts.
	//TODO//NOTE// what types of accounts is not defined in requirements and needs to be resolved.//
	
	
	//
	//Other functions addressing unknown or non-required functionality PLEASE COMMENT FUNCTIONS!//
	//
	
	
	//PLEASE COMMENT FUNCTION//
	@RequestMapping(value = "/emp2/getTransactions", method = RequestMethod.GET)
	public String getTransactionsEmp2(Model model) {
		model.addAttribute("transactionList", transactionService.getCriticalTransactions());

		return "";
	}

}
