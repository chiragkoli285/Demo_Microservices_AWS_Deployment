package com.icare.doctor.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.icare.doctor.dto.DoctorDTO;
import com.icare.doctor.service.DoctorService;

@RestController
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	private static final Log LOGGER = LogFactory.getLog(DoctorController.class);

	@GetMapping("/doctor/id/{doctorId}")
	public DoctorDTO getDoctorById(@PathVariable("doctorId") Integer doctorId) throws Exception {
		LOGGER.info("request for doctor ID : " + doctorId);
		return doctorService.getDoctorById(doctorId);
	}

	@GetMapping("doctor/name/{doctorName}")
	public DoctorDTO getDoctorByName(@PathVariable("doctorName") String doctorName) throws Exception {
		LOGGER.info("request for doctor Name : " + doctorName);
		if(doctorName.equals("Durvesh Koli")) {
			throw new RuntimeException();
		}
		return doctorService.getDoctorByName(doctorName);
	}
}
