package com.secure.bankapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByIsCritical(boolean isCritical);

	Optional<Transaction> findById(Long id);
	
	List<Transaction> findByFromAccount(Long id);
	
	
}
