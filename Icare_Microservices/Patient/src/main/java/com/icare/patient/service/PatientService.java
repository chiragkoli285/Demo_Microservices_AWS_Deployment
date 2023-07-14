package com.icare.patient.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icare.patient.dto.PatientDTO;
import com.icare.patient.entity.Patient;
import com.icare.patient.repository.PatientRepository;


@Service(value = "patientService")
@Transactional
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	public PatientDTO getPatientById(Integer patientId) throws Exception {
		Optional<Patient> patientOptional = patientRepository.findById(patientId);
		if (patientOptional.isEmpty()) {
			throw new Exception("no patient found");
		}
		Patient patient = patientOptional.get();
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setAge(patient.getAge());
		patientDTO.setDisease(patient.getDisease());
		patientDTO.setGender(patient.getGender());
		patientDTO.setPatientId(patient.getPatientId());
		patientDTO.setPatientName(patient.getPatientName());
		patientDTO.setPhoneNumber(patient.getPhoneNumber());
		return patientDTO;
	}

	public PatientDTO getPatientByName(String patientName) throws Exception {
		Patient patient = patientRepository.findByPatientName(patientName);
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setAge(patient.getAge());
		patientDTO.setDisease(patient.getDisease());
		patientDTO.setGender(patient.getGender());
		patientDTO.setPatientId(patient.getPatientId());
		patientDTO.setPatientName(patient.getPatientName());
		patientDTO.setPhoneNumber(patient.getPhoneNumber());
		return patientDTO;
	}

	public void addPatient(PatientDTO patientDTO) {
		Patient patient = new Patient();
		patient.setAddress(patient.getAddress());
		patient.setDisease(patientDTO.getDisease());
		patient.setAge(patientDTO.getAge());
		patient.setGender(patientDTO.getGender());
		patient.setPatientName(patientDTO.getPatientName());
		patient.setPhoneNumber(patient.getPhoneNumber());
		patientRepository.save(patient);
	}
}
