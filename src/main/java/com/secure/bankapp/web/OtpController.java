package com.secure.bankapp.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.secure.bankapp.service.ElemailService;
import com.secure.bankapp.service.OTPService;
import com.secure.bankapp.util.EmailTemplate;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.model.ForgotPasswordForm;
import com.secure.bankapp.model.PasswordForm;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class OtpController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserCredentialRepository rep;

	@Autowired
	public OTPService oTPService;

	@Autowired
	public ElemailService elemailService;

	@Autowired
	private UserDetailRepository userDetailRepo;

	@PostMapping("/forgotPassword")
	public String generateOtp(@ModelAttribute("forgotPasswordForm") ForgotPasswordForm form, Model model ){

		
		UserDetail user = userDetailRepo.findByUserId(form.getUserId());

		if(user== null) {
			model.addAttribute("message", "User not found");
			return "forgotpassword";
		}
		
		int otp = oTPService.generateOTP(form.getUserId());
		logger.info("Generated OTP is: "+otp);
		EmailTemplate template = new EmailTemplate("SendOtp.html");

		Map<String,String> replaceData = new HashMap<String,String>();
		replaceData.put("user", form.getUserId());
		replaceData.put("otpnum", String.valueOf(otp));

		String message = template.getTemplate(replaceData);
		String email = user.getEmail();

		elemailService.sendOtpMessage(email, "OTP -SpringBoot", message);

		

		return "validateOTP";
	}

	@RequestMapping(value ="/validateOTP", method = RequestMethod.POST)
	public  String validateOTP( @ModelAttribute("forgotPasswordForm") ForgotPasswordForm form, Model model){
		final String FAIL = "Incorrect OTP entered, Please retry!";
		if(form.getOTP() == null) {
			model.addAttribute("message", "OTP empty");
			return "validateOTP";
		}
		
		int otpnum = form.getOTP();
		//Validate the Otp
		int serverOtp = oTPService.getOtp(form.getUserId());
		if(otpnum == serverOtp){
			oTPService.clearOTP(form.getUserId());
			PasswordForm f= new PasswordForm();
			f.setUserId(form.getUserId());
			model.addAttribute("passwordForm",f );
			return "newpassword" ;
		}else{
			model.addAttribute("message", FAIL);
			return "validateOTP";
		}
	}
	   private static boolean  isBlankString(String string) {
	        return string == null || string.trim().isEmpty();
	   }
		private static final String	PASSWORD_PATTERN = "^[a-zA-Z0-9]+$";
	    private Pattern pattern;
	@RequestMapping(value ="/setPassword", method = RequestMethod.POST)
	public  String setPassword( @ModelAttribute("passwordForm") PasswordForm form, Model model){
		 if(isBlankString(form.getPassword()) || isBlankString(form.getConfirmPassword())) {
	        model.addAttribute("message", "Field cannot be empty");
	        return "newpassword";
	        }
		 if(!form.getPassword().equals(form.getConfirmPassword())) {
			 model.addAttribute("message", "Passwords should be equal");
		        return "newpassword"; 
		 }
		 
		  if (!isBlankString(form.getPassword()) && form.getPassword().length() < 8 || form.getPassword().length() > 32) {
			  model.addAttribute("message", "Passwords should be atleast 8 characters ");
		        return "newpassword"; 
	        }
	      
	        if(!isBlankString(form.getPassword()) && !pattern.matches(PASSWORD_PATTERN, form.getPassword())){
	        	 model.addAttribute("message", "Passwords should have alpha numeric");
			        return "newpassword"; 
	        }
		 
		UserCred user =  rep.findByUserId(form.getUserId());
		 BCryptPasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
		 
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		rep.save(user);
		
		return "redirect:/login";
		 
		
	}
}