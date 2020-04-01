package com.secure.bankapp.validation;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.UserProfile;
import com.secure.bankapp.service.UserService;

import ch.qos.logback.core.boolex.Matcher;

@Component
public class ProfileValidator implements Validator {
	 @Autowired
	    private UserService userService;
	 private Pattern pattern;
		private Matcher matcher;
		
		private static final String EMAIL_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		private static final String PHONE_PATTERN =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
		
		private static final String	PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
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

	        
	    
	        if(isBlankString(user.getEmail())) {
	        	errors.rejectValue("email", "cannotBeEmpty");
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
	        
	        if(!isBlankString(user.getEmail()) && !pattern.matches(PASSWORD_PATTERN,user.getAddress())) {
	        	errors.rejectValue("address", "invalid");
	        }
	        
	        if(!isBlankString(user.getEmail()) && userService.findByEmail(user.getEmail()) != null) {
	        	if(!SecurityContextHolder.getContext().getAuthentication().getName().equals(userService.findByEmail(user.getEmail()).getUserId()))
	        	errors.rejectValue("email", "emailExists");
	        }
	               
	       
	        if(!isBlankString(user.getPhone()) && !pattern.matches(PHONE_PATTERN,user.getPhone())) {
	        	errors.rejectValue("phone", "validPhone");
	        }
	        
	       
	     
	    }
}
