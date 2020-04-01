package com.secure.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.SystemLog;

public interface SystemLogRepository extends JpaRepository<SystemLog, Long>{
	
	List<SystemLog> findByUserId(String userId); 
	
	List<SystemLog> findAll();

}