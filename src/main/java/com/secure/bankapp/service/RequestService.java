package com.secure.bankapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Request;
import com.secure.bankapp.repository.RequestRepository;
import com.secure.bankapp.util.Constants;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private AccountService accountService;
	

	public void approveRequests(String[] requests) {
		// TODO Auto-generated method stub
		for(String request : requests) {
			approveRequest(Long.parseLong(request));
		}
	}

	
	public void rejectRequests(String[] Requests) {
		// TODO Auto-generated method stub
		for(String Request : Requests) {
			rejectRequest(Long.parseLong(Request));
		}
	}
	
	
	public void approveRequest(Long id) {
		// TODO Auto-generated method stub
	
		Request Request = requestRepository.findById(id).get();
		accountService.createAccount(Request.getUserId());
	
	
	
		Request.setStatus(Constants.REQUEST_STATUS.COMPLETED.toString());
		requestRepository.save(Request);
		
	}

	public void rejectRequest(Long id) {
		// TODO Auto-generated method stub
		Request Request = requestRepository.findById(id).get();
		Request.setStatus(Constants.REQUEST_STATUS.REJECTED.toString());
		requestRepository.save(Request);
		
	}


	public void saveRequest(Request Request) {
		// TODO Auto-generated method stub
		
		requestRepository.save(Request);
		
	}


	public Object getRequests(String status) {
		// TODO Auto-generated method stub
		return requestRepository.findByStatus(status);
	}

}
