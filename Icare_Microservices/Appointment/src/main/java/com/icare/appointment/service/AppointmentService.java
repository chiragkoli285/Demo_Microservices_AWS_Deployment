package com.icare.appointment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icare.appointment.dto.AppointmentDTO;
import com.icare.appointment.dto.DoctorDTO;
import com.icare.appointment.dto.PatientDTO;
import com.icare.appointment.entity.Appointment;
import com.icare.appointment.repository.AppointmentRepository;


@Service(value = "appointmentService")
@Transactional
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	public AppointmentDTO getAppointmentById(Integer appointmentId) throws Exception{
		Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
		if(appointmentOptional.isEmpty()) {
			throw new Exception("NO appointments found");
		}
		Appointment appointment = appointmentOptional.get();
		AppointmentDTO appointmentDTO = new AppointmentDTO();
		appointmentDTO.setAppointmentNo(appointment.getAppointmentNo());
		appointmentDTO.setDate(appointment.getDate());
		appointmentDTO.setStatus(appointment.getStatus());
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setPatientName(appointment.getPatientName());
		appointmentDTO.setPatientDTO(patientDTO);
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setDoctorName(appointment.getDoctorName());
		appointmentDTO.setDoctorDTO(doctorDTO);
		return appointmentDTO;
	}
}
