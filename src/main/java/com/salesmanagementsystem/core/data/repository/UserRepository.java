package com.salesmanagementsystem.core.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanagementsystem.core.model.user.User;

public interface UserRepository  extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
