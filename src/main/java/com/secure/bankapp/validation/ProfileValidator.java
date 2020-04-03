package com.secure.bankapp.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.model.UserProfile;
import com.secure.bankapp.service.SystemLogService;
import com.secure.bankapp.service.UserService;

import ch.qos.logback.core.boolex.Matcher;

@Component
public class ProfileValidator implements Validator {
	 @Autowired
	    private UserService userService;
	 private Pattern pattern;
		private Matcher matcher;
		 @Autowired
			private SystemLogService logService;
		
		private static final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		private static final String PHONE_PATTERN =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
		
		private static final String	PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
		private static final String	ADDRESS_PATTERN = "^[a-zA-Z0-9,]+$";
		
	    @Override
	    public boolean supports(Class<?> aClass) {
	        return UserProfile.class.equals(aClass);
	    }
	    
	   private static boolean  isBlankString(String string) {
	        return string == null || string.trim().isEmpty();
	    }

	    @Override
	    public void validate(Object o, Errors errors) {
	        UserProfile user = (UserProfile) o;
	        if(isBlankString(user.getFullName())) {
	        	errors.rejectValue("fullName", "cannotBeEmpty");
	        }
	        
	    
	    
	   
	        
	        if(isBlankString(user.getPhone())) {
	        	errors.rejectValue("phone", "cannotBeEmpty");
	        }
	        if(isBlankString(user.getAddress())) {
	        	errors.rejectValue("address", "cannotBeEmpty");
	        }
	        
	        
	        if(!isBlankString(user.getEmail()) && !pattern.matches(EMAIL_PATTERN,user.getEmail())) {
	        	errors.rejectValue("email", "validEmail");
	        }
	        
	        if(!isBlankString(user.getAddress()) && user.getAddress().contains("/")) {
	        	errors.rejectValue("address", "invalid");
	        	SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
				logService.recordLog(log);
	        }
	        if(!isBlankString(user.getFullName()) && !pattern.matches(PASSWORD_PATTERN,user.getFullName())) {
	        	errors.rejectValue("fullName", "invalid");
	        	SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
				logService.recordLog(log);
	        }
	        
	       
	       
	        if(!isBlankString(user.getPhone()) && !pattern.matches(PHONE_PATTERN,user.getPhone())) {
	        	errors.rejectValue("phone", "validPhone");
	        }
	        
	       
	     
	    }
}
