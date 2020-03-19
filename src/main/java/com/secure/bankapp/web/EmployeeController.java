package com.secure.bankapp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.Transaction;
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
	
	

}
