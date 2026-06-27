package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;
import com.hospital.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	@Autowired
	private PatientService patientService;

	// Add Patient
	@PostMapping
	public Patient savePatient(@Valid @RequestBody Patient patient) {

		return patientService.savePatient(patient);

	}

	/*
	 * @GetMapping public Page<Patient> getAllPatients(
	 * 
	 * @RequestParam(defaultValue="0") int page,
	 * 
	 * @RequestParam(defaultValue="5") int size){ return
	 * patientService.getAllPatients(page,size); }
	 */

	@GetMapping
	public ApiResponse<Page<Patient>> getAllPatients(

			@RequestParam(defaultValue = "0") int page,

			@RequestParam(defaultValue = "5") int size,

			@RequestParam(defaultValue = "patientName") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {

		Page<Patient> patients = patientService.getAllPatients(page, size, sortBy, direction);

		return new ApiResponse<>(true, "Patient List", patients);
	}

	// Get Patient By Id
	@GetMapping("/{id}")
	public Patient getPatientById(@PathVariable Long id) {

		return patientService.getPatientById(id);

	}

	// Update Patient
	@PutMapping("/{id}")
	public Patient updatePatient(@PathVariable Long id, @Valid @RequestBody Patient patient) {

		return patientService.updatePatient(id, patient);

	}

	// Delete Patient
	@DeleteMapping("/{id}")
	public String deletePatient(@PathVariable Long id) {

		patientService.deletePatient(id);

		return "Patient Deleted Successfully";

	}
	
	@GetMapping("/search")
	public ApiResponse<List<Patient>> searchPatient(

	        @RequestParam String patientName) {

	    List<Patient> patients =
	            patientService.searchPatientByName(patientName);

	    return new ApiResponse<>(
	            true,
	            "Patient Search Result",
	            patients);

	}

}