package com.secure.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.secure.bankapp.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
}