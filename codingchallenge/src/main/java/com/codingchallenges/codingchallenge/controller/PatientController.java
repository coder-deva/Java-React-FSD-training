package com.codingchallenges.codingchallenge.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenges.codingchallenge.model.Patient;
import com.codingchallenges.codingchallenge.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	// add patient
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Patient patient){
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(patientService.add(patient));
	}
	
	
	
	
	@GetMapping("/byDoctorId/{doctorId}")
	public ResponseEntity<List<Patient>> getPatientsByDoctor(@PathVariable int doctorId) {
	    List<Patient> patients = patientService.getPatientsByDoctorId(doctorId);
	    return ResponseEntity
	    		.status(HttpStatus.CREATED)
	    		.body(patients);
	    		
	}
	
	

}
