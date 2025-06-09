package com.codingchallenges.codingchallenge.controller;
import java.security.Principal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenges.codingchallenge.model.User;
import com.codingchallenges.codingchallenge.service.UserService;
import com.codingchallenges.codingchallenge.util.JwtUtil;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	 // insert user in db with encrypted password
	 
	@PostMapping("/signup")
	public ResponseEntity<User> signUp(@RequestBody User user ) {
	    User savedUser = userService.signUp(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	
	@GetMapping("/token")
	public ResponseEntity<?> getToken(Principal principal) {
		try {
		String token =jwtUtil.createToken(principal.getName()); 
		return ResponseEntity.status(HttpStatus.OK).body(token);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

}
