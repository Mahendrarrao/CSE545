package com.secure.bankapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.bankapp.exception.InsufficientBalanceException;
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
	public List<Transaction> getTransactionsByAccountId(Long id, String status) {
		// TODO Auto-generated method stub
		List<Transaction> fromList =  transactionRepository.findByFromAccountAndStatus(id, status);
		List<Transaction> toList =  transactionRepository.findByToAccountAndStatus(id, status);
		toList.addAll(fromList);
	
		return toList;
	}

	@Override
	public List<Transaction> getCriticalTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findByIsCriticalAndStatus(true, Constants.TRANSACTION_STATUS.PENDING_APPROVAL.toString());
	}

	@Override
	public List<Transaction> getNonCriticalTransactions() {
		// TODO Auto-generated method stub
		return transactionRepository.findByIsCriticalAndStatus(false, Constants.TRANSACTION_STATUS.PENDING_APPROVAL.toString());
	}

	@Override
	public void approveTransaction(Long id) {
		// TODO Auto-generated method stub
	
		Transaction transaction = transactionRepository.findById(id).get();
		Account fromAcc = accountService.getAccountById(transaction.getFromAccount()).get();
		Account toAcc = accountService.getAccountById(transaction.getToAccount()).get();
	
		accountService.creditFunds(toAcc, transaction);
		try {
			accountService.debitFunds(fromAcc, transaction);
		} catch (InsufficientBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		transaction.setStatus(Constants.TRANSACTION_STATUS.COMPLETED.toString());
		transactionRepository.save(transaction);
		
	}

	@Override
	public void rejectTransaction(Long id) {
		// TODO Auto-generated method stub
		Transaction transaction = transactionRepository.findById(id).get();
		transaction.setStatus(Constants.TRANSACTION_STATUS.REJECTED.toString());
		transactionRepository.save(transaction);
		
	}

	@Override
	public void saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
		transactionRepository.save(transaction);
		
	}

	@Override
	public void approveTransactions(String[] transactions) {
		// TODO Auto-generated method stub
		for(String transaction : transactions) {
			approveTransaction(Long.parseLong(transaction));
		}
	}

	@Override
	public void rejectTransactions(String[] transactions) {
		// TODO Auto-generated method stub
		for(String transaction : transactions) {
			rejectTransaction(Long.parseLong(transaction));
		}
	}

	@Override
	public void generateStatement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		List<Transaction> fromList =  transactionRepository.findByFromAccount(accountId);
		List<Transaction> toList =  transactionRepository.findByToAccount(accountId);
		toList.addAll(fromList);
	
		return toList;
	}

}
