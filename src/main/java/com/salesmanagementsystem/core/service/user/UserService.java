package com.salesmanagementsystem.core.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.salesmanagementsystem.core.data.repository.UserRepository;
import com.salesmanagementsystem.core.model.user.User;

public class UserService {

      @Autowired
    private UserRepository userRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;


    public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	public User findById(long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

    public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User newUser = userRepository.save(user);
		return newUser; 
	}
}
