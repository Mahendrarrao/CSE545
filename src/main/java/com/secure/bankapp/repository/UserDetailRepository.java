package com.secure.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;

public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
	
	UserDetail findByEmail(String email);
	UserDetail findByuserId(String userId);

}
