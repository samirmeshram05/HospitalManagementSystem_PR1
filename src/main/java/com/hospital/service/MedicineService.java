package com.hospital.service;

import java.util.List;

import com.hospital.entity.Medicine;

public interface MedicineService {

    Medicine saveMedicine(Medicine medicine);

    List<Medicine> getAllMedicines();

    Medicine getMedicineById(Long id);

    Medicine updateMedicine(Long id, Medicine medicine);

    void deleteMedicine(Long id);

}