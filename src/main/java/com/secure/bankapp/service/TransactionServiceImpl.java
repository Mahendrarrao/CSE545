package com.secure.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.repository.TransactionRepository;
import com.secure.bankapp.util.Constants;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private AccountService accountService;
	@Override
	public Transaction getTransactionByid(Long id) {
		// TODO Auto-generated method stub
		return transactionRepository.findById(id).get();
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(Long id) {
		// TODO Auto-generated method stub
		return transactionRepository.findByFromAccount(id);
	}

	@Override
	public List<Transaction> getCriticalTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findByIsCritical(true);
	}

	@Override
	public List<Transaction> getNonCriticalTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findByIsCritical(false);
	}

	@Override
	public void approveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
	
		Account fromAcc = accountService.getAccountById(transaction.getFromAccount()).get();
		Account toAcc = accountService.getAccountById(transaction.getToAccount()).get();
		accountService.transferFunds(toAcc, fromAcc, transaction.getTransactionValue());
		transaction.setStatus(Constants.TRANSACTION_STATUS.COMPLETED.toString());
		
	}

	@Override
	public void rejectTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		transaction.setStatus(Constants.TRANSACTION_STATUS.REJECTED.toString());
		
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
		transactionRepository.save(transaction);
		
	}

	@Override
	public void approveTransactions(List<Transaction> transactions) {
		// TODO Auto-generated method stub
		for(Transaction transaction : transactions) {
			approveTransaction(transaction);
		}
	}

	@Override
	public void rejectTransactions(List<Transaction> transactions) {
		// TODO Auto-generated method stub
		for(Transaction transaction : transactions) {
			rejectTransaction(transaction);
		}
	}

}
