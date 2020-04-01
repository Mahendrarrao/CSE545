package com.secure.bankapp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secure.bankapp.exception.InsufficientBalanceException;
import com.secure.bankapp.model.Account;
import com.secure.bankapp.model.Request;
import com.secure.bankapp.repository.RequestRepository;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.util.Constants;
import com.secure.bankapp.util.Email1Template;
import com.secure.bankapp.util.EmailTemplate;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	public ElemailService elemailService;
	
@Autowired
private UserDetailRepository userDetailRepository;
	

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
		if (Request.getRequestType().equals(Constants.REQUEST_TYPE.NEWACCOUNT.toString())) {
		accountService.createAccount(Request.getUserId());
		
		} else {
			Email1Template template = new Email1Template("help.html");

			Map<String,String> replaceData = new HashMap<String,String>();
			replaceData.put("user", Request.getUserId());
			replaceData.put("date", Request.getAdate());

			String message = template.getTemplate(replaceData);
			elemailService.sendOtpMessage(userDetailRepository.findByUserId(Request.getUserId()).getEmail(), "Appointment Details", message);	
			
		}
	
	
	
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


	public Object getRequests(String status, String type) {
		// TODO Auto-generated method stub
		
		return requestRepository.findByStatusAndRequestType(status, type);
	}

}
