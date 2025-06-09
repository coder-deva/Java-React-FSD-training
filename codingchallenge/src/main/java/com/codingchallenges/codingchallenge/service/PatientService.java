package com.codingchallenges.codingchallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingchallenges.codingchallenge.model.Doctor;
import com.codingchallenges.codingchallenge.model.Patient;
import com.codingchallenges.codingchallenge.model.User;
import com.codingchallenges.codingchallenge.repository.DoctorRepository;
import com.codingchallenges.codingchallenge.repository.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;

	@Autowired
	private UserService userService;
	
	

	public PatientService(PatientRepository patientRepository, UserService userService) {
		super();
		this.patientRepository = patientRepository;
		this.userService = userService;
	}



	public Patient add(Patient patient) {
		
		User user=patient.getUser();
		//set role
		user.setRole("PATIENT");
		
		user=userService.signUp(user);
		patient.setUser(user);
		return patientRepository.save(patient);
	}
	
	public List<Patient> getPatientsByDoctorId(int doctorId) {
	    return patientRepository.getByDoctor(doctorId);
	}

}
