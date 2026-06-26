package com.hospital.service;

import org.springframework.data.domain.Page;

import com.hospital.entity.Patient;

public interface PatientService {

    Patient savePatient(Patient patient);

    //List<Patient> getAllPatients();
    Page<Patient> getAllPatients(int page,int size);

    Patient getPatientById(Long id);

    Patient updatePatient(Long id, Patient patient);

    void deletePatient(Long id);

}