package com.has.users.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.has.users.entities.User;
import com.has.users.exceptions.UserNotFoundException;
import com.has.users.repositories.IUserRepository;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	
	private final IUserRepository userRepository;
	
	public UsersController(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping()
	public List<User> getAll(){
		return userRepository.findAll();
	}
	
	@PostMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public User createUser(@RequestBody User newUser) {
		newUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(newUser.getPassword()));
		return userRepository.save(newUser);
	}
	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException(userId));
	}
	
	@DeleteMapping()
	@PreAuthorize("hasAuthority('ADMIN')")
	public void deleteUser(@PathVariable Long userId) {
		userRepository.deleteById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUser(@PathVariable Long userId, @RequestBody User userToUpdate) {
		
	    User user =
	        userRepository
	            .findById(userId)
	            .orElseThrow(() -> new UserNotFoundException(userId));
	    user.setEmail(userToUpdate.getEmail());
	    user.setLastName(userToUpdate.getLastName());
	    user.setFirstName(userToUpdate.getFirstName());
	    user.setBirthDate(userToUpdate.getBirthDate());
	    user.setPhone(userToUpdate.getPhone());
	    
		return userRepository.save(userToUpdate);
	}

}
