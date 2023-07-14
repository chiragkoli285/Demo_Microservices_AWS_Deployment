package com.icare.appointment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.icare.appointment.dto.DoctorDTO;
import com.icare.appointment.dto.PatientDTO;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;

@Service
public class AppointmentCircuitBreakerService {

	@Autowired
	RestTemplate restTemplate;

	@CircuitBreaker(name = "appointmentService")
	public Future<PatientDTO> getPatientByName(String patientName) {
		return Future
				.of(() -> restTemplate.getForObject("http://PatientMS/patient/name/" + patientName, PatientDTO.class));
	}

	public Future<DoctorDTO> getDoctorByName(String doctorName) {
		return Future.of(() -> restTemplate.getForObject("http://DoctorMS/doctor/name/" + doctorName, DoctorDTO.class));
	}
}
