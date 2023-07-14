package com.icare.appointment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icare.appointment.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
