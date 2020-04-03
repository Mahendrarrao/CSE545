package com.secure.bankapp.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.service.SystemLogService;
import com.secure.bankapp.service.UserService;

import ch.qos.logback.core.boolex.Matcher;



@Component
public class UserModifyValidator implements Validator {
    @Autowired
    private UserService userService;
    
    private Pattern pattern;
	private Matcher matcher;
	
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static final String PHONE_PATTERN =  "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
	
	private static final String	PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
	
	@Autowired
	private SystemLogService logService;
	
	
	
    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationForm.class.equals(aClass);
    }
    
   public static boolean  isBlankString(String string) {
        return string == null || string.trim().isEmpty();
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDetail user = (UserDetail) o;

        
        
        if(isBlankString(user.getUserId())) {
        	errors.rejectValue("userId", "cannotBeEmpty");
        }
     
        if(isBlankString(user.getEmail())) {
        	errors.rejectValue("email", "cannotBeEmpty");
        }
        if(isBlankString(user.getFullName())) {
        	errors.rejectValue("fullName", "cannotBeEmpty");
        }
        
   
        
        if(isBlankString(user.getPhone())) {
        	errors.rejectValue("phone", "cannotBeEmpty");
        }
        if(isBlankString(user.getAddress())) {
        	errors.rejectValue("address", "cannotBeEmpty");
        }
       
        
        	
  
        
        if (!isBlankString(user.getAddress()) && user.getAddress().contains("<")) {
            errors.rejectValue("address", "invalid");
            SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }
     
        if (!isBlankString(user.getFullName()) && user.getFullName().contains("<")) {
            errors.rejectValue("fullName", "invalid");
            SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }
        
       
        if(!isBlankString(user.getEmail()) && !pattern.matches(EMAIL_PATTERN,user.getEmail())) {
        	errors.rejectValue("email", "validEmail");
        }
      
        
        if(!isBlankString(user.getEmail()) && userService.findByEmail(user.getEmail()) != null && !userService.findByEmail(user.getEmail()).getUserId().equals(user.getUserId())) {
        	errors.rejectValue("email", "emailExists");
        }
        
        
       
        if(!isBlankString(user.getPhone()) && !pattern.matches(PHONE_PATTERN,user.getPhone()) ) {
        	errors.rejectValue("phone", "validPhone");
        }
        
        if(!isBlankString(user.getEmail()) && userService.findByPhone(user.getPhone()) != null && !userService.findByPhone(user.getPhone()).getUserId().equals(user.getUserId())) {
        	errors.rejectValue("phone", "phoneExists");
        }
        
        
       
     
    }
}