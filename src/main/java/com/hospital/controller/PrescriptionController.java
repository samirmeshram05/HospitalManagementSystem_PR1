package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Prescription;
import com.hospital.service.PrescriptionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // Add Prescription
    @PostMapping
    public Prescription savePrescription(@Valid @RequestBody Prescription prescription) {

        return prescriptionService.savePrescription(prescription);
    }

    // Get All Prescriptions
    @GetMapping
    public List<Prescription> getAllPrescriptions() {

        return prescriptionService.getAllPrescriptions();
    }

    // Get Prescription By Id
    @GetMapping("/{id}")
    public Prescription getPrescriptionById(@PathVariable Long id) {

        return prescriptionService.getPrescriptionById(id);
    }

    // Update Prescription
    @PutMapping("/{id}")
    public Prescription updatePrescription(@PathVariable Long id,
            @Valid @RequestBody Prescription prescription) {

        return prescriptionService.updatePrescription(id, prescription);
    }

    // Delete Prescription
    @DeleteMapping("/{id}")
    public String deletePrescription(@PathVariable Long id) {

        prescriptionService.deletePrescription(id);

        return "Prescription Deleted Successfully";
    }
}