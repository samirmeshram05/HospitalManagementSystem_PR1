package com.hospital.service;

import java.util.List;

import com.hospital.entity.Doctor;

public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long id);

    Doctor updateDoctor(Long id, Doctor doctor);

    List<Doctor> getAllDoctorsSorted();
    
    void deleteDoctor(Long id);
    
    List<Doctor> findBySpecialization( String specialization );

}