package com.codingchallenges.codingchallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingchallenges.codingchallenge.model.MedicalHistory;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {

    List<MedicalHistory> findByPatientId(int patientId);

}
