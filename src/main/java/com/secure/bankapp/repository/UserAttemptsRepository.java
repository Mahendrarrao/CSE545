package com.secure.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.UserAttempts;


public interface UserAttemptsRepository extends JpaRepository<UserAttempts, Long>{
	
	UserAttempts getByUserId(String userId);
	

}
