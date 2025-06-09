package com.codingchallenges.codingchallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codingchallenges.codingchallenge.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer>{

	@Query("select d from Doctor d where d.user.username=?1")
	Doctor getByUsername(String username);

}