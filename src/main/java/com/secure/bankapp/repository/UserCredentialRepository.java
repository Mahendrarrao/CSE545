package com.secure.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.UserCred;

public interface UserCredentialRepository extends JpaRepository<UserCred, String> {
    UserCred findByUserId(String username);
}