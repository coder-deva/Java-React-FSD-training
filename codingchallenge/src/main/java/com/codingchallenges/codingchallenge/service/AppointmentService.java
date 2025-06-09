package com.codingchallenges.codingchallenge.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.codingchallenges.codingchallenge.exception.ResourceNotFoundException;
import com.codingchallenges.codingchallenge.model.Appointment;
import com.codingchallenges.codingchallenge.model.Doctor;
import com.codingchallenges.codingchallenge.model.Patient;
import com.codingchallenges.codingchallenge.repository.AppointmentRepository;
import com.codingchallenges.codingchallenge.repository.DoctorRepository;
import com.codingchallenges.codingchallenge.repository.PatientRepository;

@Service
public class AppointmentService {
	
	private AppointmentRepository appointmentRepository;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;
	
	
	public AppointmentService(AppointmentRepository appointmentRepository, PatientRepository patientRepository,
			DoctorRepository doctorRepository) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}
	
	public Appointment add(int patient_id, int doctor_id) {
		//Fetch patient
		Patient patient=patientRepository.findById(patient_id)
				.orElseThrow(()->new ResourceNotFoundException("Patient id not found"));
		//Fetch doctor
		Doctor doctor=doctorRepository.findById(doctor_id)
				.orElseThrow(()->new ResourceNotFoundException("Doctor id not found"));
		Appointment appointment=new Appointment();
		
		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setAppointmentDateTime(LocalDateTime.now());
		return appointmentRepository.save(appointment);
	}

}
