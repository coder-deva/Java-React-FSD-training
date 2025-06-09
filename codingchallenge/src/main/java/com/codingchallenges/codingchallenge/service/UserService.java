package com.codingchallenges.codingchallenge.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.codingchallenges.codingchallenge.model.User;
import com.codingchallenges.codingchallenge.repository.UserRepository;


@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signUp(User user) {
		
		
		String plainPassword = user.getPassword(); //plaintext
		String encodedPassword =  passwordEncoder.encode(plainPassword);//this will encrypt the password
		user.setPassword(encodedPassword); 
		
		return userRepository.save(user);
	}
	
	public User findByUsername(String username) {
	    return userRepository.getByUsername(username);
	}


	
	
}
