package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Doctor saveDoctor(@Valid @RequestBody Doctor doctor) {

        return doctorService.saveDoctor(doctor);

    }

    // Get All Doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {

        return doctorService.getAllDoctors();

    }

    // Get Doctor By Id
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {

        return doctorService.getDoctorById(id);

    }

    // Update Doctor
    @PutMapping("/{id}")
    public Doctor updateDoctor(@PathVariable Long id,
                               @Valid @RequestBody Doctor doctor) {

        return doctorService.updateDoctor(id, doctor);

    }

    // Delete Doctor
    @DeleteMapping("/{id}")
    public String deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return "Doctor Deleted Successfully";

    }
    
    @GetMapping("/sorted")
    public List<Doctor> getDoctorsSorted(){
        return doctorService.getAllDoctorsSorted();
    }
    
    @GetMapping("/specialization/{specialization}")
    public List<Doctor> findDoctor(@PathVariable String specialization){
    	return doctorService.findBySpecialization( specialization);
    }

}