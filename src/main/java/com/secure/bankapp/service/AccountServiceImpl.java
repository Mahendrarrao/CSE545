package com.secure.bankapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.AccountRepository;
import com.secure.bankapp.util.Constants;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		
		accountRepository.save(account);
		
		
	}

	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
		accountRepository.delete(account);
		
	}


	@Override
	public List<Account> getAccountsByUserId(String userId) {
		// TODO Auto-generated method stub
		return accountRepository.findByUserId(userId);
	}

	@Override
	public Optional<Account> getAccountById(Long id) {
		// TODO Auto-generated method stub
		return accountRepository.findById(id);
	}

	@Override
	public Optional<Account> getAccountByPhone(String phone) {
		// TODO Auto-generated method stub
		
		String userId = userService.findByPhone(phone).getUserId();
		return accountRepository.findByUserIdAndDefaultAccount(userId, true);
		
	}

	@Override
	public Optional<Account> getAccountByEmail(String email) {
		// TODO Auto-generated method stub
		
		String userId = userService.findByEmail(email).getUserId();
		return accountRepository.findByUserIdAndDefaultAccount(userId, true);
		
	}

	@Override
	public void debitFunds(Account account, Transaction transaction) throws InsufficientBalanceException {
		// TODO Auto-generated method stub
		
		Double balance = account.getBalance();
		Double afterAction = balance - transaction.getTransactionValue() ;
		if(afterAction < 0) {
			transaction.setStatus(Constants.TRANSACTION_STATUS.FAILED.toString());
			transactionService.saveTransaction(transaction);
			throw new InsufficientBalanceException("Insufficient Balance");
		} else {
			
			account.setBalance(afterAction);
			transaction.setStatus(Constants.TRANSACTION_STATUS.COMPLETED.toString());
			transactionService.saveTransaction(transaction);
			
		}
			
		
	}

	@Override
	public void creditFunds(Account account,Transaction transaction) {
		// TODO Auto-generated method stub
		
		try {
			Double afterAction = account.getBalance() + transaction.getTransactionValue();
			account.setBalance(afterAction);
			transaction.setStatus(Constants.TRANSACTION_STATUS.COMPLETED.toString());
			transactionService.saveTransaction(transaction);
			
		} catch(Exception e) {
			transaction.setStatus(Constants.TRANSACTION_STATUS.FAILED.toString());
			transactionService.saveTransaction(transaction);
		}
		
	}

	@Override
	public void transferFunds(Account toAccount, Account fromAccount, Double funds) {
		// TODO Auto-generated method stub
		
		if (funds > 1000) {
			
			Transaction transaction = new Transaction();
			transaction.setFromAccount(fromAccount.getAccountId());
			transaction.setToAccount(toAccount.getAccountId());
			transaction.setTransactionValue(funds);
			transaction.setIsCritical(true);
			transaction.setStatus(Constants.TRANSACTION_STATUS.PENDING_APPROVAL.toString());
			transaction.setTransactionDate(Date.valueOf(LocalDate.now()));
			transaction.setUserId(toAccount.getUserId());
			transactionService.saveTransaction(transaction);
			
			
			
		} else {
			
			Transaction transaction = new Transaction();
			transaction.setFromAccount(fromAccount.getAccountId());
			transaction.setToAccount(toAccount.getAccountId());
			transaction.setTransactionValue(funds);
			transaction.setIsCritical(false);
			transaction.setStatus(Constants.TRANSACTION_STATUS.PENDING_APPROVAL.toString());
			transaction.setTransactionDate(Date.valueOf(LocalDate.now()));
			transaction.setUserId(toAccount.getUserId());
			transactionService.saveTransaction(transaction);
			
		}
		
	}

	@Override
	public void transferByPhone(String phone, String userId, Double funds) {
		// TODO Auto-generated method stub
	Account fromAccount = accountRepository.findByUserIdAndDefaultAccount(userId, true).get();
	Account toAccount = accountRepository.findByUserIdAndDefaultAccount(userService.findByPhone(phone).getUserId(), true).get();
		
	if (fromAccount != null && toAccount != null) {
		transferFunds(toAccount, fromAccount, funds);
		
	}
		
	}

	@Override
	public void transferByEmail(String email, String userId, Double funds) {
		// TODO Auto-generated method stub
		
		Account fromAccount = accountRepository.findByUserIdAndDefaultAccount(userId, true).get();
		Account toAccount = accountRepository.findByUserIdAndDefaultAccount(userService.findByEmail(email).getUserId(), true).get();
			
		if (fromAccount != null && toAccount != null) {
			transferFunds(toAccount, fromAccount, funds);
			
		}
		
	}

	@Override
	public void generateBankStatement(Account account) {
		// TODO Auto-generated method stub
		
	}

	

}
