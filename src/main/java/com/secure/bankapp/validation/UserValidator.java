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
import com.secure.bankapp.service.SystemLogService;
import com.secure.bankapp.service.UserService;

import ch.qos.logback.core.boolex.Matcher;



@Component
public class UserValidator implements Validator {
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
        RegistrationForm user = (RegistrationForm) o;

        
        
        if(isBlankString(user.getUserId())) {
        	errors.rejectValue("userId", "cannotBeEmpty");
        }
        if(isBlankString(user.getPassword())) {
        	errors.rejectValue("password", "cannotBeEmpty");
        }
        if(isBlankString(user.getConfirmPassword())) {
        	errors.rejectValue("confirmPassword", "cannotBeEmpty");
        }
        if(isBlankString(user.getEmail())) {
        	errors.rejectValue("email", "cannotBeEmpty");
        }
        if(isBlankString(user.getFirstName())) {
        	errors.rejectValue("firstName", "cannotBeEmpty");
        }
        
        if(isBlankString(user.getLastName())) {
        	errors.rejectValue("lastName", "cannotBeEmpty");
        }
        
        if(isBlankString(user.getPhone())) {
        	errors.rejectValue("phone", "cannotBeEmpty");
        }
        if(isBlankString(user.getAddress())) {
        	errors.rejectValue("address", "cannotBeEmpty");
        }
        
        if(isBlankString(user.getCity())) {
        	errors.rejectValue("city", "cannotBeEmpty");
        }
        
        	
        if (!isBlankString(user.getUserId()) && (user.getUserId().length() < 6 || user.getUserId().length() > 10)) {
            errors.rejectValue("userId", "userIdLength");
        }
        if (!isBlankString(user.getUserId()) && userService.findByUsername(user.getUserId()) != null) {
           
        	errors.rejectValue("userId", "userExists");
        	SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }
        
        if (!isBlankString(user.getUserId()) && userService.findByUsername(user.getUserId()) != null) {
            errors.rejectValue("userId", "userExists");
        }
        
        if (!isBlankString(user.getUserId()) && !pattern.matches(PASSWORD_PATTERN, user.getUserId())) {
            errors.rejectValue("userId", "invalid");
            SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }
        
        if (!isBlankString(user.getAddress()) && user.getAddress().contains("<")) {
            errors.rejectValue("address", "invalid");
            SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }
        if (!isBlankString(user.getCity()) && !pattern.matches(PASSWORD_PATTERN, user.getCity())) {
            errors.rejectValue("city", "invalid");
        }
        if (!isBlankString(user.getFirstName()) && !pattern.matches(PASSWORD_PATTERN, user.getFirstName())) {
            errors.rejectValue("firstName", "invalid");
            SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }
        if (!isBlankString(user.getLastName()) && !pattern.matches(PASSWORD_PATTERN, user.getLastName())) {
            errors.rejectValue("lastName", "invalid");
            SystemLog log = new SystemLog(SecurityContextHolder.getContext().getAuthentication().getName(), "Malicious input entered", java.sql.Date.valueOf(LocalDate.now()));
			logService.recordLog(log);
        }

       
        if (!isBlankString(user.getPassword()) && user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "passwordLength");
        }
      
        if(!isBlankString(user.getPassword()) && !pattern.matches(PASSWORD_PATTERN, user.getPassword())){
        	errors.rejectValue("password", "passwordAlpha");
        }
        
        if (!isBlankString(user.getConfirmPassword()) && !user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "passwordMatch");
        }
        
       
        if(!isBlankString(user.getEmail()) && !pattern.matches(EMAIL_PATTERN,user.getEmail())) {
        	errors.rejectValue("email", "validEmail");
        }
        
        if(!isBlankString(user.getEmail()) && userService.findByEmail(user.getEmail()) != null) {
        	errors.rejectValue("email", "emailExists");
        }
        
        
       
        if(!isBlankString(user.getPhone()) && !pattern.matches(PHONE_PATTERN,user.getPhone())) {
        	errors.rejectValue("phone", "validPhone");
        }
        
        if(!isBlankString(user.getEmail()) && userService.findByPhone(user.getPhone()) != null) {
        	errors.rejectValue("phone", "phoneExists");
        }
        
        
       
     
    }
}