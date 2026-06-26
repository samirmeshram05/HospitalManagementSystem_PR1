package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Appointment;
import com.hospital.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Book Appointment
    @PostMapping
    public Appointment bookAppointment(@Valid @RequestBody Appointment appointment) {

        return appointmentService.bookAppointment(appointment);

    }

    // Get All Appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {

        return appointmentService.getAllAppointments();

    }

    // Get Appointment By Id
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Long id) {

        return appointmentService.getAppointmentById(id);

    }

    // Update Appointment
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id,
                                         @Valid @RequestBody Appointment appointment) {

        return appointmentService.updateAppointment(id, appointment);

    }

    // Delete Appointment
    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return "Appointment Deleted Successfully";

    }

}