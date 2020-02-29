package com.secure.bankapp.service;

import com.secure.bankapp.model.User;

public interface UserService {

	void save(User user);

    User findByUsername(String username);
}
