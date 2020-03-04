package com.secure.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.model.UserAttempts;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long>{
	
	List<SystemLog> findByUserId(String userId); 

}
