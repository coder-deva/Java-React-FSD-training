package com.codingchallenges.codingchallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenges.codingchallenge.model.Doctor;
import com.codingchallenges.codingchallenge.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Doctor doctor){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(doctorService.add(doctor));
	}

}
