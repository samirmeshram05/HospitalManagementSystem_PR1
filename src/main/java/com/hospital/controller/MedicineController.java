package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.entity.Medicine;
import com.hospital.service.MedicineService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    // Add Medicine
    @PostMapping
    public Medicine saveMedicine(@Valid @RequestBody Medicine medicine) {
        return medicineService.saveMedicine(medicine);
    }

    // Get All Medicines
    @GetMapping
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    // Get Medicine By Id
    @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }

    // Update Medicine
    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Long id,
            @Valid @RequestBody Medicine medicine) {

        return medicineService.updateMedicine(id, medicine);
    }

    // Delete Medicine
    @DeleteMapping("/{id}")
    public String deleteMedicine(@PathVariable Long id) {

        medicineService.deleteMedicine(id);

        return "Medicine Deleted Successfully";
    }

}