package com.secure.bankapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Request;
import com.secure.bankapp.model.Transaction;

public interface RequestRepository extends JpaRepository<Request, Long> {
	
	List<Request> findByStatus(String status);



	List<Request> findByStatusAndRequestType(String status, String type);

}
