package com.secure.bankapp.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.secure.bankapp.model.Search;
import com.secure.bankapp.service.UserService;

@Component
public class SearchValidator implements Validator {
	HashMap<Long, HashSet<Long>> managerToUser= new HashMap<Long, HashSet<Long>>() {{
	    put(4L,new HashSet<>((Arrays.asList(2L, 3L, 4L))));
	    put(3L,new HashSet<>(Arrays.asList(0L)));
	    put(2L,new HashSet<>(Arrays.asList(0L)));
	}};
	
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
        
        Long user = userService.findByUsername(search.getUserName()).getRoleId();
        Long manager = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getRoleId();
        Set<Long> userSet = managerToUser.get(manager);
        System.out.println(userSet);
        if (!userSet.contains(user)) {
        	
          	errors.rejectValue("userName", "notFound");
        }
        
		
	}

}
