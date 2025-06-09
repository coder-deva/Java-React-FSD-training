package com.codingchallenges.codingchallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingchallenges.codingchallenge.model.Doctor;
import com.codingchallenges.codingchallenge.model.User;
import com.codingchallenges.codingchallenge.repository.DoctorRepository;

@Service
public class DoctorService {


	private DoctorRepository doctorRepository;

	@Autowired
	private UserService userService;
	
	public DoctorService(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}

	public Doctor add(Doctor doctor) {
		
		User user=doctor.getUser();
		//set role
		user.setRole("DOCTOR");
		
		user=userService.signUp(user);
		doctor.setUser(user);
		return doctorRepository.save(doctor);
	}

}
