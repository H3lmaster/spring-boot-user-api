package com.has.users.controllers;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.has.users.entities.User;
import com.has.users.repositories.IUserRepository;

@RestController
@RequestMapping("/register")
public class RegistrationController {

	private final IUserRepository userRepository;
	
	public RegistrationController(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping()
	public User register(@RequestBody User newUser) {
		newUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}
	
}