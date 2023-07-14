package com.icare.doctor.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.icare.doctor.dto.DoctorDTO;
import com.icare.doctor.entity.Doctor;
import com.icare.doctor.repository.DoctorRepository;

import jakarta.transaction.Transactional;

@Service(value = "doctorService")
@Transactional
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	public DoctorDTO getDoctorById(Integer doctorId) throws Exception {
		Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
		if (doctorOptional.isEmpty()) {
			throw new Exception("NO doctor found on given Id");
		}
		Doctor doctor = doctorOptional.get();
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setDegree(doctor.getDegree());
		doctorDTO.setDoctorId(doctor.getDoctorId());
		doctorDTO.setDoctorName(doctor.getDoctorName());
		doctorDTO.setLevel(doctor.getDoctorName());
		doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
		return doctorDTO;
	}
	
	public DoctorDTO getDoctorByName(String doctorName) throws Exception {
		Doctor doctor = doctorRepository.findByDoctorName(doctorName);
		if(doctor == null) {
			throw new Exception("No doctor found on given name");
		}
		DoctorDTO doctorDTO = new DoctorDTO();
		doctorDTO.setDegree(doctor.getDegree());
		doctorDTO.setDoctorId(doctor.getDoctorId());
		doctorDTO.setDoctorName(doctor.getDoctorName());
		doctorDTO.setLevel(doctor.getLevel());
		doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
		return doctorDTO;
	}
}
