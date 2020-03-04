package com.secure.bankapp.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;

import com.secure.bankapp.model.UserAttempts;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.repository.UserAttemptsRepository;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.util.Constants;

@Service
public class UserAttemptsServiceImpl implements UserAttemptsService {
	
	@Autowired
	private UserAttemptsRepository userAttemptsRepository;
	
	@Autowired
	private UserCredentialRepository userCred;

	@Override
	public void updateFailAttempts(String username) {
		// TODO Auto-generated method stub
		
		  UserAttempts user = getUserAttempts(username);
		  if (user == null) {
			if (isUserExists(username)) {
				// if no record, insert a new
				user.setAttempts(1);
				user.setLastModified(Date.valueOf(LocalDate.now()));
				userAttemptsRepository.save(user);
				
			}
		  } else {

			if (isUserExists(username)) {
				// update attempts count, +1
				user.setLastModified(Date.valueOf(LocalDate.now()));
				user.setAttempts(user.getAttempts() + 1);
				userAttemptsRepository.save(user);
			}

			if (user.getAttempts() + 1 >= Constants.MAX_ATTEMPTS) {
				// locked user
				UserCred u = userCred.findByUserId(username);
				u.setStatus(Constants.USER_LOCKED);
				userCred.save(u);
			
				
				// throw exception
				throw new LockedException("User Account is locked!");
			}

		  }

	}

	private boolean isUserExists(String username) {
		// TODO Auto-generated method stub
		return userCred.findByUserId(username) != null;
	}

	@Override
	public void resetFailAttempts(String username) {
		// TODO Auto-generated method stub
		  UserAttempts user = getUserAttempts(username);
		  if(user==null) {
			  user = new UserAttempts();
			  user.setAttempts(0);
			  user.setUserId(username);
			  userAttemptsRepository.save(user);
			  return;
			  
		  }
		  user.setAttempts(0);
		  user.setLastModified(null);
		  userAttemptsRepository.save(user);

	}

	@Override
	public UserAttempts getUserAttempts(String username) {
		// TODO Auto-generated method stub
		return userAttemptsRepository.getByUserId(username);
	}

}
