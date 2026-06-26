package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Patient;
import com.hospital.service.PatientService;

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

    // Get All Patients
    @GetMapping
    public List<Patient> getAllPatients() {

        return patientService.getAllPatients();

    }

    // Get Patient By Id
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {

        return patientService.getPatientById(id);

    }

    // Update Patient
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id,
            @Valid @RequestBody Patient patient) {

        return patientService.updatePatient(id, patient);

    }

    // Delete Patient
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Long id) {

        patientService.deletePatient(id);

        return "Patient Deleted Successfully";

    }

}