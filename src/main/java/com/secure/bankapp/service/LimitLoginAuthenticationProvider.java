package com.secure.bankapp.service;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.model.UserAttempts;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.util.Constants;

@Component("authenticationProvider")
public class LimitLoginAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	private UserAttemptsService userAttemptsService;
	
	@Autowired
	private SystemLogService logService;
	
	@Autowired
	private UserCredentialRepository userCredentialRepository;
	
	@Autowired
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}
	
	@Override
	public Authentication authenticate(Authentication authentication) 
          throws AuthenticationException {

	  try {

		Authentication auth = super.authenticate(authentication);
			
		//if reach here, means login success, else an exception will be thrown
		//reset the user_attempts
		userAttemptsService.resetFailAttempts(authentication.getName());
		SystemLog log = new SystemLog(authentication.getName(), Constants.USER_LOGIN, java.sql.Date.valueOf(LocalDate.now()));
		logService.recordLog(log);
		
		
			
		return auth;
			
	  } catch (BadCredentialsException e) {	
			String error = "";
			  UserCred user = userCredentialRepository.findByUserId(authentication.getName());
			  if (user.getStatus().equals(Constants.PASS_CHANGE)) {
				  error = "Please change password through Forgot Password"; 
				  throw new LockedException(error);
			  }
		//invalid login, update to user_attempts
		  userAttemptsService.updateFailAttempts(authentication.getName());
		
		
		  throw e;
			
	  } catch (LockedException e){
			
		//this user is locked!
			String error = "";
		
		 

		UserAttempts userAttempts = 
				userAttemptsService.getUserAttempts(authentication.getName());
		
               if(userAttempts!=null){
			Date lastAttempts = userAttempts.getLastModified();
			error = "Account is locked due to incorrect login attempts! Contact Bank.";
		}else{
			error = e.getMessage();
		}
			
	  throw new LockedException(error);
	}

	}
}
