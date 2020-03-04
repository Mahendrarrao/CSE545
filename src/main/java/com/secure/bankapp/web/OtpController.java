package com.secure.bankapp.web;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.secure.bankapp.service.ElemailService;
import com.secure.bankapp.service.OTPService;
import com.secure.bankapp.util.EmailTemplate;
import com.secure.bankapp.repository.UserDetailRepository;
import com.secure.bankapp.model.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class OtpController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public OTPService oTPService;

	@Autowired
	public ElemailService elemailService;

	@Autowired
	private UserDetailRepository userDetailRepo;

	@GetMapping("/generateOtp")
	public String generateOtp(Model model, @RequestParam("referer") String referer){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		UserDetail user = userDetailRepo.findByuserId(userId);

		int otp = oTPService.generateOTP(userId);
		logger.info("Generated OTP is: "+otp);
		EmailTemplate template = new EmailTemplate("SendOtp.html");

		Map<String,String> replaceData = new HashMap<String,String>();
		replaceData.put("user", userId);
		replaceData.put("otpnum", String.valueOf(otp));

		String message = template.getTemplate(replaceData);
		String email = user.getEmail();

		elemailService.sendOtpMessage(email, "OTP -SpringBoot", message);

		model.addAttribute("referer", referer);

		return "pageToBeDeveloped";
	}

	@RequestMapping(value ="/validateOTP", method = RequestMethod.GET)
	public @ResponseBody String validateOTP(@RequestParam("otpnum") int otpnum, @RequestParam("referer") String referer){
		final String FAIL = "Incorrect OTP entered, Please retry!";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		//Validate the Otp
		int serverOtp = oTPService.getOtp(username);
		if(otpnum == serverOtp){
			oTPService.clearOTP(username);
			List<GrantedAuthority> updatedAuthorities = new ArrayList<GrantedAuthority>(auth.getAuthorities());
			updatedAuthorities.add(new SimpleGrantedAuthority("OTP_AUTH"));
			Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(newAuth);
			return referer;
		}else{
			return FAIL;
		}
	}
}