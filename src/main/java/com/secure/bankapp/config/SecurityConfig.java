package com.secure.bankapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.secure.bankapp.service.LimitLoginAuthenticationProvider;
import com.secure.bankapp.util.Constants;
import com.secure.bankapp.util.CustomLogoutHandler;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	   @Autowired
	    private UserDetailsService userDetailsService;
	   
	   @Autowired
		@Qualifier("authenticationProvider")
		AuthenticationProvider authenticationProvider;

	    @Bean
	    public BCryptPasswordEncoder bCryptPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http .csrf().disable()
	                .authorizeRequests()
	                    .antMatchers("/resources/**", "/register", "/forgotPassword").permitAll()
	                    .antMatchers("/emp2/**").hasRole("TIER2")
	                    .antMatchers("/emp1/**").hasRole("TIER1")
	                    .and()
	                .formLogin()
	                    .loginPage("/login")
	                    .permitAll()
	                    .and()
	                .logout()
	                .addLogoutHandler(customLogoutHandler())
	                    .permitAll();
	                  

	        
	    }

	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    	
	    	 LimitLoginAuthenticationProvider provider = (LimitLoginAuthenticationProvider)authenticationProvider;
	    	    BCryptPasswordEncoder passwordEncoder = bCryptPasswordEncoder();
	    	 provider.setPasswordEncoder(passwordEncoder);
	    	 auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	    	    auth.authenticationProvider(authenticationProvider);
	        
	        
	    }
	    
	    @Bean
	    public CustomLogoutHandler customLogoutHandler() {
	    	return new CustomLogoutHandler();
	    }
}
