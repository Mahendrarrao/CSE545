package com.secure.bankapp.service;

import com.secure.bankapp.model.UserCred;
import com.secure.bankapp.model.UserDetail;

public interface UserService {

	void save(UserCred user, UserDetail userDetail);

    UserCred findByUsername(String username);
    
    UserDetail findByEmail(String username);
}
