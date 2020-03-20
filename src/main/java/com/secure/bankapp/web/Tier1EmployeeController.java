package com.secure.bankapp.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.service.AccountService;
import com.secure.bankapp.service.TransactionService;

@Controller
public class Tier1EmployeeController {

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserDetailRepository UserDetailRepository;

	//Begin Mappings for Tier 1 Employees
	//Functions addressing functionality described in Course Project Requirements Document namely,
	//
	//can view, create and authorize non-critical transactions upon having authorization from the bank customer and tier 2 employee
	//can view customers’ accounts
	//can issue cashier cheques as well as handle fund transfer
	//can authorize or decline customer’s request
	//
	
	//Tier 1 Employees can view non-critical transactions upon having authorization from the bank customer and tier 2 employee.
	@RequestMapping(value = "/emp1/getNonCriticalTransactions", method = RequestMethod.GET)
	public String getTransactionsEmp1(Model model) {
		// TODO
		// Needs authentication that transactions returned to tier1 employee are all nonCritical with authentication
		// from tier2 employee and customer.
		model.addAttribute("transactionList", transactionService.getNonCriticalTransactions());

		return "";
	}
	
	
	//Tier 1 Employees can authorize (approve) non-critical transactions upon having authorization from the bank customer and tier 2 employee.
	@RequestMapping(value = "/emp1/approveNonCriticalTransactions", method = RequestMethod.POST)
	public String approveTransactionsEmp1(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {
		// TODO
		// Needs authentication that transactions supplied by tier1 employee are all nonCritical with authentication
		// from tier2 employee and customer.
		transactionService.approveTransactions(transactions);

		return "";
	}

	
	//Tier 1 Employees can authorize (reject) non-critical transactions upon having authorization from the bank customer and tier 2 employee.
	@RequestMapping(value = "/emp1/rejectNonCriticalTransactions", method = RequestMethod.POST)
	public String rejectTransactionsEmp1(@ModelAttribute("transactions") List<Transaction> transactions, Model model) {
		// TODO
		// Needs authentication that transactions supplied by tier1 employee are all nonCritical with authentication
		// from tier2 employee and customer.
		transactionService.rejectTransactions(transactions);

		return "";
	}
	
	//Tier 1 Employees can create non-critical transactions upon having authorization from the bank customer and tier 2 employee.
	//TODO//
	
	
	//Tier 1 Employees can view customers’ accounts.
	//TODO//
	
	
	//Tier 1 Employees can issue cashier checks as well as handle fund transfer.
	//TODO//
	
	
	//Tier 1 Employees can authorize customer’s request.
	//TODO//
	
	
	//Tier 1 Employees can reject customer’s request.
	//TODO//
	
	
	//
	//Other functions addressing unknown or non-required functionality PLEASE COMMENT FUNCTIONS!//
	//
	
	//PLEASE COMMENT FUNCTION//
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
	
	
	//PLEASE COMMENT FUNCTION//
	@RequestMapping(value = {"/emp1/add/" }, method = RequestMethod.POST)
	public String addFunds(@ModelAttribute("transaction") Transaction transaction, Model model) {

		transaction.setTransactionType("Credit");
		accountService.creditFunds(accountService.getAccountById(transaction.getToAccount()).get(), transaction);
		
		return "";
	}
	
	
	//PLEASE COMMENT FUNCTION//
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
