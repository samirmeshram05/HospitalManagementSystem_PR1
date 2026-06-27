package com.hospital.service;

import java.time.LocalDate;
import java.util.List;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentStatus;

public interface AppointmentService {

    Appointment bookAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    Appointment getAppointmentById(Long id);

    Appointment updateAppointment(Long id, Appointment appointment);

    void deleteAppointment(Long id);
    
    List<Appointment> searchByStatus(AppointmentStatus status);

    List<Appointment> searchByDate(LocalDate appointmentDate);

    List<Appointment> searchByDoctor(String doctorName);

    List<Appointment> searchByPatient(String patientName);

}