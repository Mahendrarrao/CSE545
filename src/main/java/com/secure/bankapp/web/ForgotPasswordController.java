package com.secure.bankapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secure.bankapp.model.ForgotPasswordForm;

@Controller
public class ForgotPasswordController {
	 @RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	    public String forgotPassword(Model model) {
	        model.addAttribute("forgotPasswordForm", new ForgotPasswordForm());

	        return "forgotpassword";
	    }
}
