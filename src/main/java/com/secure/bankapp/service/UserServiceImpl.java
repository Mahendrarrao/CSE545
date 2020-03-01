package com.secure.bankapp.service;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.secure.bankapp.model.Role;
import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;
import com.secure.bankapp.repository.RoleRepository;
import com.secure.bankapp.repository.UserCredentialRepository;
import com.secure.bankapp.repository.UserDetailRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserCredentialRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public void save(UserCred user, UserDetail userDetail) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	
		userRepository.save(user);
		userDetailRepository.save(userDetail);
	}

	@Override
	public UserCred findByUsername(String username) {
		return userRepository.findByUserId(username);
	}

	@Override
	public UserDetail findByEmail(String email) {
		
		return userDetailRepository.findByEmail(email);
	}
}
