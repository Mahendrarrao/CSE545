package com.secure.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}