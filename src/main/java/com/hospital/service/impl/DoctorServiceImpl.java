package com.hospital.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hospital.entity.Department;
import com.hospital.entity.Doctor;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DepartmentRepository;
import com.hospital.repository.DoctorRepository;
import com.hospital.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {

        if (doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (doctorRepository.existsByMobile(doctor.getMobile())) {
            throw new RuntimeException("Mobile Number already exists");
        }

        Long departmentId = doctor.getDepartment().getDepartmentId();

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with ID : " + departmentId));

        doctor.setDepartment(department);

        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(Long id) {

        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found with ID : " + id));
    }

    @Override
    public Doctor updateDoctor(Long id, Doctor doctor) {

        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found with ID : " + id));

        existingDoctor.setDoctorName(doctor.getDoctorName());
        existingDoctor.setSpecialization(doctor.getSpecialization());
        existingDoctor.setQualification(doctor.getQualification());
        existingDoctor.setExperience(doctor.getExperience());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setMobile(doctor.getMobile());

        Long departmentId = doctor.getDepartment().getDepartmentId();

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with ID : " + departmentId));

        existingDoctor.setDepartment(department);

        return doctorRepository.save(existingDoctor);
    }

    @Override
    public void deleteDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found with ID : " + id));

        doctorRepository.delete(doctor);
    }
    
    @Override
    public List<Doctor> getAllDoctorsSorted(){
        return doctorRepository.findAll(
            Sort.by("doctorName")
        );
    }
    
    @Override
    public List<Doctor> findBySpecialization( String specialization){    	
    	return doctorRepository.findBySpecialization( specialization);
    }

}