package com.secure.bankapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Transaction;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.AccountRepository;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.util.Constants;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	@Autowired
	private UserDetailRepository UserDetailRepository;
	
	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		
		accountRepository.save(account);
		
		
	}

	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
		
		accountRepository.delete(account);
		List<Account> list= accountRepository.findByUserId(account.getUserId());
		if(list.size() > 0) {
		Account acc=	list.get(0);
		acc.setDefaultAccount(true);
		accountRepository.save(acc);
			
		}
		
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
		if (userId == null) {
			return null;
		}
		
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
			accountRepository.save(account);
			
		}
			
		
	}

	@Override
	public void creditFunds(Account account,Transaction transaction) {
		// TODO Auto-generated method stub
		
		try {
			Double afterAction = account.getBalance() + transaction.getTransactionValue();
			account.setBalance(afterAction);
			accountRepository.save(account);
			
		} catch(Exception e) {
			transaction.setStatus(Constants.TRANSACTION_STATUS.FAILED.toString());
			transactionService.saveTransaction(transaction);
		}
		
	}
	
	@Override
	public void creditFundsByEmp1(Account account) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		try {
		
			Account acc1= accountRepository.findById(account.getAccountId()).get();
			transaction.setToAccount(0000000001L);
			transaction.setFromAccount(account.getAccountId());
			transaction.setTransactionDate(Date.valueOf(LocalDate.now()));
			transaction.setTransactionValue(account.getBalance());
			transaction.setUserId(acc1.getUserId());
			transaction.setStatus(Constants.TRANSACTION_STATUS.COMPLETED.toString());
			transaction.setTransactionType(Constants.TRANSACTION_TYPE.CREDIT.toString());
			Double afterAction = acc1.getBalance() + account.getBalance();
			acc1.setBalance(afterAction);
			accountRepository.save(acc1);
			transactionService.saveTransaction(transaction);
		
			
		} catch(Exception e) {
			transaction.setStatus(Constants.TRANSACTION_STATUS.FAILED.toString());
			transactionService.saveTransaction(transaction);
		}
		
	}
	
	@Override
	public void debitFundsByEmp1(Account account) {
		// TODO Auto-generated method stub
		Transaction transaction = new Transaction();
		try {
		
			Account acc1= accountRepository.findById(account.getAccountId()).get();
			transaction.setFromAccount(0000000001L);
			transaction.setToAccount(account.getAccountId());
			transaction.setTransactionDate(Date.valueOf(LocalDate.now()));
			transaction.setTransactionValue(account.getBalance());
			transaction.setTransactionType(Constants.TRANSACTION_TYPE.DEBIT.toString());
			transaction.setStatus(Constants.TRANSACTION_STATUS.COMPLETED.toString());
			Double afterAction = acc1.getBalance() - account.getBalance();
			acc1.setBalance(afterAction);
			accountRepository.save(acc1);
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

	
	@Override
	@Transactional
	public void deleteByUserId(String userName) {
		// TODO Auto-generated method stub
		UserDetailRepository.deleteById(userName);
		userCredentialRepository.deleteById(userName);
		accountRepository.deleteByUserId(userName);
	}

	@Override
	public void createAccount(String userId) {
		// TODO Auto-generated method stub
		
	Account  account = new Account();
	account.setAccountId(createAccountNumber());
	account.setBalance(0.0);
	account.setCreatedDate(Date.valueOf(LocalDate.now()));
	account.setUserId(userId);
	account.setUpdatedDate(Date.valueOf(LocalDate.now()));
	if (accountRepository.findByUserId(userId).size() == 0) {
		account.setDefaultAccount(true);
		
	}
	account.setAccountStatus(Constants.ACCOUNT_STATUS.ACTIVE.toString());
	accountRepository.save(account);
		
	}
	
	private Long createAccountNumber() {
		int min = 1000000000;
		int max = 2000000000;
		Random r = new Random();
		while (true) {
			int no = r.nextInt((max - min) +1) + min;
			Long l = new Long(no);
			if (!accountRepository.findById(l).isPresent() )
				return l;
		}
	
	
	}

	@Override
	public void setDefaultAccount(Account acc1) {
		// TODO Auto-generated method stub
		acc1.setDefaultAccount(true);
		for(Account a: getAccountsByUserId(acc1.getUserId())) {
			if( acc1.getAccountId() != a.getAccountId()) {
				a.setDefaultAccount(false);
				saveAccount(a);
			}
		}
		
		saveAccount(acc1);
	}

	

}
