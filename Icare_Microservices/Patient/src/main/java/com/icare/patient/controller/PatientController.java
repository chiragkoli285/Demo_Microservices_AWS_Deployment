package com.icare.patient.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.icare.patient.dto.PatientDTO;
import com.icare.patient.service.PatientService;

@RestController
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	private static final Log LOGGER = LogFactory.getLog(PatientController.class); 

	@GetMapping("/patient/id/{patientId}")
	public PatientDTO getpatientById(@PathVariable("patientId") Integer patientId) throws Exception {
		LOGGER.info("request for patient ID : "+patientId);
		return patientService.getPatientById(patientId);
	}

	@GetMapping("/patient/name/{patientName}")
	public PatientDTO getPatientByName(@PathVariable("patientName") String patientName) throws Exception {
		LOGGER.info("request for patient Name : "+patientName);
		return patientService.getPatientByName(patientName);
	}
}
