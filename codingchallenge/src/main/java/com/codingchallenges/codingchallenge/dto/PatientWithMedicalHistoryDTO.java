package com.codingchallenges.codingchallenge.dto;

import java.util.List;

import com.codingchallenges.codingchallenge.model.MedicalHistory;

public class PatientWithMedicalHistoryDTO {

    private String name;
    private int age;
    private List<MedicalHistoryDTO> medicalHistories;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<MedicalHistoryDTO> getMedicalHistories() {
		return medicalHistories;
	}
	public void setMedicalHistories(List<MedicalHistoryDTO> medicalHistories) {
		this.medicalHistories = medicalHistories;
	}
    
    

    

    

   
}
