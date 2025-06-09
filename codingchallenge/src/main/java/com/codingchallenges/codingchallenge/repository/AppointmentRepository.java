package com.codingchallenges.codingchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenges.codingchallenge.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}
