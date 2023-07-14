package com.icare.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icare.doctor.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	public Doctor findByDoctorName(String doctorName);
}
