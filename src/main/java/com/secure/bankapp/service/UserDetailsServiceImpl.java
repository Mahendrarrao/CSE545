package com.secure.bankapp.service;

import java.util.HashSet;
import java.util.Set;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.secure.bankapp.model.Role;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.repository.RoleRepository;
import com.secure.bankapp.repository.UserCredentialRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserCredentialRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserCred user = userRepository.findByUserId(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		/*
		 * for (Role role : user.getRoles()){ grantedAuthorities.add(new
		 * SimpleGrantedAuthority(role.getName())); }
		 */
      Role role =  roleRepository.findByRoleId(user.getRoleId());
      
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
       

        return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), grantedAuthorities);
    }
}