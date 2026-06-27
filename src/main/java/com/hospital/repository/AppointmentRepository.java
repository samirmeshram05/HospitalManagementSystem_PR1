package com.hospital.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByAppointmentStatus(AppointmentStatus appointmentStatus);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> findByDoctorDoctorNameContainingIgnoreCase(String doctorName);

    List<Appointment> findByPatientPatientNameContainingIgnoreCase(String patientName);

    long countByAppointmentStatus(AppointmentStatus appointmentStatus);
}