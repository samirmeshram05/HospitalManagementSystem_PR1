package com.hospital.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Appointment;
import com.hospital.entity.AppointmentStatus;
import com.hospital.service.AppointmentService;
import com.hospital.util.ApiResponse;

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
	public Appointment updateAppointment(@PathVariable Long id, @Valid @RequestBody Appointment appointment) {

		return appointmentService.updateAppointment(id, appointment);

	}

	// Delete Appointment
	@DeleteMapping("/{id}")
	public String deleteAppointment(@PathVariable Long id) {

		appointmentService.deleteAppointment(id);

		return "Appointment Deleted Successfully";

	}

	@GetMapping("/search/status")
	public ApiResponse<List<Appointment>> searchByStatus(

			@RequestParam AppointmentStatus status) {

		return new ApiResponse<>(

				true,

				"Appointment Search Result",

				appointmentService.searchByStatus(status));

	}

	@GetMapping("/search/date")
	public ApiResponse<List<Appointment>> searchByDate(

			@RequestParam LocalDate appointmentDate) {

		return new ApiResponse<>(

				true,

				"Appointment Search Result",

				appointmentService.searchByDate(appointmentDate));

	}

	@GetMapping("/search/doctor")
	public ApiResponse<List<Appointment>> searchByDoctor(

			@RequestParam String doctorName) {

		return new ApiResponse<>(

				true,

				"Appointment Search Result",

				appointmentService.searchByDoctor(doctorName));

	}

	@GetMapping("/search/patient")
	public ApiResponse<List<Appointment>> searchByPatient(

			@RequestParam String patientName) {

		return new ApiResponse<>(

				true,

				"Appointment Search Result",

				appointmentService.searchByPatient(patientName));

	}

}