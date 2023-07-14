package com.icare.appointment.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.icare.appointment.dto.AppointmentDTO;
import com.icare.appointment.dto.DoctorDTO;
import com.icare.appointment.dto.PatientDTO;
import com.icare.appointment.service.AppointmentCircuitBreakerService;
import com.icare.appointment.service.AppointmentService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.vavr.concurrent.Future;

@RestController
// @LoadBalancerClient(name = "MyLoadBalancer", configuration = LoadBalancerConfig.class)
public class AppointmentController {
	
	@Autowired
	private AppointmentCircuitBreakerService appointmentCircuitBreakerService;
	
	@Autowired
	private AppointmentService appointmentService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient client;
	
	private static final Log LOGGER = LogFactory.getLog(AppointmentController.class);
	
	private String doctorUri; //  = "http://localhost:8200";
	
	private String patientUri; //  = "http://localhost:8100";
	
	@CircuitBreaker(fallbackMethod = "getAppointmentDetailsFallback", name = "appointmentService")
	@GetMapping("/appointment/{appointmentId}")
	public AppointmentDTO getAppointmentDetails(@PathVariable("appointmentId") Integer appointmentId) throws Exception {
		AppointmentDTO appointmentDTO = appointmentService.getAppointmentById(appointmentId);
		
//		List<ServiceInstance> listDoctorMSInstances = client.getInstances("DoctorMS");
//		if(listDoctorMSInstances != null && listDoctorMSInstances.size() > 0) {
//			doctorUri = listDoctorMSInstances.get(0).getUri().toString();
//		}
//		
//		DoctorDTO doctorDTO = restTemplate.getForObject("http://DoctorMS/"+"/doctor/name/"+appointmentDTO.getDoctorDTO().getDoctorName(), DoctorDTO.class);
		
		Future<DoctorDTO> doctorDTO = appointmentCircuitBreakerService.getDoctorByName(appointmentDTO.getDoctorDTO().getDoctorName());
			
		LOGGER.info("request for doctor details : "+doctorDTO);
		
//		List<ServiceInstance> listPatientMSInstances = client.getInstances("PatientMS");
//		if(listPatientMSInstances != null && listPatientMSInstances.size() > 0) {
//			patientUri = listPatientMSInstances.get(0).getUri().toString();
//		}
//		PatientDTO patientDTO = restTemplate.getForObject("http://PatientMS/"+"/patient/name/"+appointmentDTO.getPatientDTO().getPatientName(), PatientDTO.class);
		
		Future<PatientDTO> patientDTO = appointmentCircuitBreakerService.getPatientByName(appointmentDTO.getPatientDTO().getPatientName());
		
		LOGGER.info("request for patient details : "+patientDTO);
		appointmentDTO.setDoctorDTO(doctorDTO.get());
		
		appointmentDTO.setPatientDTO(patientDTO.get());
		
		return appointmentDTO;
	}
	
	
	public AppointmentDTO getAppointmentDetailsFallback(Integer appointmentId, Throwable throwable) {
		LOGGER.info("------------in fallback------------");
		return new AppointmentDTO();
	}
}
