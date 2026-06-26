package com.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hospital.entity.Doctor;
import com.hospital.entity.Patient;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Patient savePatient(Patient patient) {

        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (patientRepository.existsByMobile(patient.getMobile())) {
            throw new RuntimeException("Mobile Number already exists");
        }

        Long doctorId = patient.getDoctor().getDoctorId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found with ID : " + doctorId));

        patient.setDoctor(doctor);

        return patientRepository.save(patient);
    }

    @Override
    public Page<Patient> getAllPatients(int page,int size){

        return patientRepository.findAll(PageRequest.of(page,size));

    }

    @Override
    public Patient getPatientById(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with ID : " + id));
    }

    @Override
    public Patient updatePatient(Long id, Patient patient) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with ID : " + id));

        existingPatient.setPatientName(patient.getPatientName());
        existingPatient.setAge(patient.getAge());
        existingPatient.setGender(patient.getGender());
        existingPatient.setMobile(patient.getMobile());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setAddress(patient.getAddress());
        existingPatient.setBloodGroup(patient.getBloodGroup());

        Long doctorId = patient.getDoctor().getDoctorId();

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found with ID : " + doctorId));

        existingPatient.setDoctor(doctor);

        return patientRepository.save(existingPatient);
    }

    @Override
    public void deletePatient(Long id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient not found with ID : " + id));

        patientRepository.delete(patient);
    }
}