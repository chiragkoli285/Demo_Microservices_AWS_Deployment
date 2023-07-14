package com.icare.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icare.patient.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

	
	public Patient findByPatientName(String patientName);
}
