package com.secure.bankapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.UserCred;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Optional<Account> findById(Long id);
	List<Account> findByUserId(String userId);
	
	Optional<Account> findByUserIdAndDefaultAccount(String id, boolean defaultAccount);
}
