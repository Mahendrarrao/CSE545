package com.secure.bankapp.service;

import com.secure.bankapp.model.UserAttempts;

public interface UserAttemptsService {

	void updateFailAttempts(String username);
	void resetFailAttempts(String username);
	UserAttempts getUserAttempts(String username);
}
