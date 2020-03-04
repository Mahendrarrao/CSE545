package com.secure.bankapp.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.service.SystemLogService;

public class CustomLogoutHandler implements LogoutHandler {
	 @Autowired
	    private SystemLogService logService;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
		
	    SystemLog log = new SystemLog(authentication.getName(), Constants.USER_LOGOUT, Date.valueOf(LocalDate.now()));
	    logService.recordLog(log);
	    
	}

}
