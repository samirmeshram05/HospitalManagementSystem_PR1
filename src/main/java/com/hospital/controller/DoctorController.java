package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.DoctorDTO;
import com.hospital.entity.Doctor;
import com.hospital.service.DoctorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	// Add Doctor
	@PostMapping
	public DoctorDTO saveDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {

		return doctorService.saveDoctor(doctorDTO);

	}

	// Get All Doctors
	@GetMapping
	public List<DoctorDTO> getAllDoctors() {

		return doctorService.getAllDoctors();

	}

	// Get Doctor By Id
	@GetMapping("/{id}")
	public DoctorDTO getDoctorById(@PathVariable Long id) {

		return doctorService.getDoctorById(id);

	}

	// Update Doctor
	@PutMapping("/{id}")
	public DoctorDTO updateDoctor(

	        @PathVariable Long id,

	        @Valid @RequestBody DoctorDTO doctorDTO){

	    return doctorService.updateDoctor(id, doctorDTO);

	}

	// Delete Doctor
	@DeleteMapping("/{id}")
	public String deleteDoctor(@PathVariable Long id) {

		doctorService.deleteDoctor(id);

		return "Doctor Deleted Successfully";

	}

	@GetMapping("/sorted")
	public List<Doctor> getDoctorsSorted() {
		return doctorService.getAllDoctorsSorted();
	}

	@GetMapping("/specialization/{specialization}")
	public List<Doctor> findDoctor(@PathVariable String specialization) {
		return doctorService.findBySpecialization(specialization);
	}

}