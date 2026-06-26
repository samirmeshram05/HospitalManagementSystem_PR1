package com.hospital.service;

import java.util.List;

import com.hospital.entity.Prescription;

public interface PrescriptionService {

    Prescription savePrescription(Prescription prescription);

    List<Prescription> getAllPrescriptions();

    Prescription getPrescriptionById(Long id);

    Prescription updatePrescription(Long id, Prescription prescription);

    void deletePrescription(Long id);

}