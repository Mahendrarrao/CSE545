package com.secure.bankapp.service;

import java.util.List;

import com.secure.bankapp.model.Transaction;

public interface TransactionService {

	public Transaction getTransactionByid(Long id);
	
	public List<Transaction> getTransactionsByAccountId(Long id);
	
	public List<Transaction> getCriticalTransactions();
	
	public List<Transaction> getNonCriticalTransactions();
	
	public void approveTransaction(Transaction transaction);
	
	public void rejectTransaction(Transaction transaction);
	
	public void saveTransaction(Transaction transaction);

	public void approveTransactions(List<Transaction> transactions);

	public void rejectTransactions(List<Transaction> transactions);
	
}
