package com.icare.appointment.dto;

import java.time.LocalDate;

public class AppointmentDTO {
	private Integer appointmentNo;
	private PatientDTO patientDTO;
	private DoctorDTO doctorDTO;
	private String status;
	private LocalDate date;
	public Integer getAppointmentNo() {
		return appointmentNo;
	}
	public void setAppointmentNo(Integer appointmentNo) {
		this.appointmentNo = appointmentNo;
	}
	public PatientDTO getPatientDTO() {
		return patientDTO;
	}
	public void setPatientDTO(PatientDTO patientDTO) {
		this.patientDTO = patientDTO;
	}
	public DoctorDTO getDoctorDTO() {
		return doctorDTO;
	}
	public void setDoctorDTO(DoctorDTO doctorDTO) {
		this.doctorDTO = doctorDTO;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
