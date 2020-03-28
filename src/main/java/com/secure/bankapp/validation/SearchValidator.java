package com.secure.bankapp.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.Search;
import com.secure.bankapp.model.UserProfile;
import com.secure.bankapp.service.UserService;

@Component
public class SearchValidator implements Validator {
	 @Autowired
	    private UserService userService;
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Search.class.equals(clazz);
	}
	   private static boolean  isBlankString(String string) {
	        return string == null || string.trim().isEmpty();
	    }
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		
		Search search = (Search) target;

	        
	        
	        if(isBlankString(search.getUserName())) {
	        	errors.rejectValue("userName", "cannotBeEmpty");
	        	return;
	        }
	        
	        if (userService.findByUsername(search.getUserName()).getRoleId() != 0 ) {
	        	
	          	errors.rejectValue("userName", "notFound");
	        }
	        
		
	}

}
