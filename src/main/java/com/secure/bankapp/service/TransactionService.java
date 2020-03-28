package com.secure.bankapp.service;

import java.util.List;

import com.secure.bankapp.model.Transaction;

public interface TransactionService {

	public Transaction getTransactionByid(Long id);
	
	public List<Transaction> getTransactionsByAccountId(Long id);
	
	public List<Transaction> getCriticalTransactions();
	
	public List<Transaction> getNonCriticalTransactions();
	
	public void approveTransaction(Long transaction);
	
	public void rejectTransaction(Long transaction);
	
	public void saveTransaction(Transaction transaction);

	public void approveTransactions(String[] list);

	void rejectTransactions(String[] transactions);
	
}
