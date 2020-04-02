package com.secure.bankapp.web;

import java.time.LocalDate;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secure.bankapp.model.ForgotPasswordForm;
import com.secure.bankapp.model.RegistrationForm;
import com.secure.bankapp.model.SystemLog;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.service.SystemLogService;
import com.secure.bankapp.service.UserService;
import com.secure.bankapp.util.Constants;
import com.secure.bankapp.validation.UserValidator;


@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    
 
    
   

    @Autowired
    private UserValidator userValidator;
    
    @Autowired
    private OtpController otpController;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new RegistrationForm());

        return "registration2";
    }
    
    @RequestMapping(value = {"/invalid", "/expired"}, method = RequestMethod.GET)
    public String invalid(Model model) {
       

        return "invalid";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("userForm") RegistrationForm userForm, BindingResult bindingResult, Model model) {
       userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration2";
        }
        
      //  userForm.setUserType(userType);
        UserCred userCred = new UserCred();
        userCred.setUserId(userForm.getUserId());
        userCred.setPassword(userForm.getPassword());
        userCred.setRoleId(Long.parseLong(userForm.getCustomerType()));
        userCred.setStatus(Constants.VERIFY_NEEDED);
        
        UserDetail userDetail = new UserDetail();
        userDetail.setFullName(userForm.getFirstName() + " " + userForm.getLastName());
        userDetail.setUserId(userForm.getUserId());
        userDetail.setEmail(userForm.getEmail());
        userDetail.setPhone(userForm.getPhone());
        userDetail.setCreatedAt(Date.valueOf(LocalDate.now()));
        userDetail.setUpdatedOn(Date.valueOf(LocalDate.now()));
        userDetail.setDob(Date.valueOf(LocalDate.now()));
        userDetail.setGender("Male");
        userDetail.setAddress(userForm.getAddress() + ", " + userForm.getCity());
        
        

        userService.save(userCred, userDetail);
        
ForgotPasswordForm form = new ForgotPasswordForm();
form.setUserId(userForm.getUserId());
        return otpController.generateOtp1(form, model);
    }
    


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout,HttpServletRequest request) {
    	if (error != null) {

    		if (getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION").contains(Constants.VERIFY_NEEDED)) {
    			
    			ForgotPasswordForm form = new ForgotPasswordForm();
    			form.setUserId(getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION").split(",")[1]);
    			
    			
    			
    			 return otpController.generateOtp1(form, model);
    		}
            model.addAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
    	}

        if (logout != null)
        {
            model.addAttribute("message", "You have been logged out successfully.");
            
        
            
        }

        return "login";
    }
    
  //customize the error message
  	private String getErrorMessage(HttpServletRequest request, String key){
  	
  		Exception exception = 
                     (Exception) request.getSession().getAttribute(key);
  		
  		String error = "";
  		if (exception instanceof BadCredentialsException) {
  			error = "Invalid username or password!";
  		}else if(exception instanceof LockedException) {
  			error = exception.getMessage();
  		}else{
  			error = "Invalid username or password";
  		}
  		
  		return error;
  	}

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String welcome(Model model) {
    	User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (user != null) {
    	String role = user.getAuthorities().iterator().next().getAuthority().toString();
    	
    	if(role.equals(Constants.ROLE_CUSTOMER))
    		return "redirect:user/home";
    	
  
    	
    	if(role.equals(Constants.ROLE_TIER1))
    		return "redirect:emp1/home";
    	
    	if(role.equals(Constants.ROLE_TIER2))
    		return "redirect:emp2/home";
    	
    	if(role.equals(Constants.ROLE_ADMIN))
    		return "redirect:admin/home";
    	}
    	
        return "login";
    }
    
   
}