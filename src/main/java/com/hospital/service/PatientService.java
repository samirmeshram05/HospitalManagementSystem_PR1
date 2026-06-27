package com.hospital.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hospital.entity.Patient;

public interface PatientService {

	Patient savePatient(Patient patient);

	Page<Patient> getAllPatients(int page, int size, String sortBy, String direction);

	Patient getPatientById(Long id);

	Patient updatePatient(Long id, Patient patient);

	void deletePatient(Long id);

	List<Patient> searchPatientByName(String patientName);
}