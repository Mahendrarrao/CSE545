package com.secure.bankapp.service;

import java.util.List;
import java.util.Optional;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;

public interface AccountService {

	public void saveAccount(Account account);
	
	public void deleteAccount(Account account);
	
	public Optional<Account> getAccountById(Long id);
	
	public Optional<Account> getAccountByPhone(String phone);
	
	public Optional<Account> getAccountByEmail(String email);
	
	public List<Account> getAccountsByUserId(String userId);
	
	public void debitFunds(Account account, Transaction transaction) throws InsufficientBalanceException;
	
	public void creditFunds(Account account, Transaction transaction);
	
	public void transferFunds(Account toAccount, Account  fromAccount ,Double funds);
	

	

	
	public void generateBankStatement(Account account);

	void transferByPhone(String phone, String userId, Double funds);

	void transferByEmail(String email, String userId, Double funds);

	public void deleteByUserId(String userName);

	void creditFundsByEmp1(Account account);

	void debitFundsByEmp1(Account account);

	public void createAccount(String userId);
	
	
	
	
	
	
	
}
