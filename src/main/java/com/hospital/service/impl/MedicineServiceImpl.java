package com.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.Medicine;
import com.hospital.entity.Prescription;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.MedicineRepository;
import com.hospital.repository.PrescriptionRepository;
import com.hospital.service.MedicineService;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Override
    public Medicine saveMedicine(Medicine medicine) {

        Long prescriptionId = medicine.getPrescription().getPrescriptionId();

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Prescription not found with ID : " + prescriptionId));

        medicine.setPrescription(prescription);

        return medicineRepository.save(medicine);
    }

    @Override
    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    @Override
    public Medicine getMedicineById(Long id) {

        return medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with ID : " + id));
    }

    @Override
    public Medicine updateMedicine(Long id, Medicine medicine) {

        Medicine existing = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with ID : " + id));

        existing.setMedicineName(medicine.getMedicineName());
        existing.setMedicineType(medicine.getMedicineType());
        existing.setDosage(medicine.getDosage());
        existing.setQuantity(medicine.getQuantity());
        existing.setManufacturer(medicine.getManufacturer());

        Long prescriptionId = medicine.getPrescription().getPrescriptionId();

        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Prescription not found with ID : " + prescriptionId));

        existing.setPrescription(prescription);

        return medicineRepository.save(existing);
    }

    @Override
    public void deleteMedicine(Long id) {

        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Medicine not found with ID : " + id));

        medicineRepository.delete(medicine);
    }
}