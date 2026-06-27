package com.hospital.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hospital.dto.DoctorDTO;
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
    private ModelMapper modelMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

   /* @Override
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
    }*/
    
    @Override
    public DoctorDTO saveDoctor(DoctorDTO dto) {

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found"));

        Doctor doctor = modelMapper.map(dto, Doctor.class);

        doctor.setDepartment(department);

        Doctor savedDoctor = doctorRepository.save(doctor);

        DoctorDTO response = modelMapper.map(savedDoctor, DoctorDTO.class);

        response.setDepartmentId(department.getDepartmentId());
        response.setDepartmentName(department.getDepartmentName());

        return response;
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {

        return doctorRepository.findAll()

                .stream()

                .map(doctor -> {

                    DoctorDTO dto =
                            modelMapper.map(doctor, DoctorDTO.class);

                    dto.setDepartmentId(
                            doctor.getDepartment().getDepartmentId());

                    dto.setDepartmentName(
                            doctor.getDepartment().getDepartmentName());

                    return dto;

                })

                .toList();

    }

    @Override
    public DoctorDTO getDoctorById(Long id) {

        Doctor doctor = doctorRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found"));

        DoctorDTO dto = modelMapper.map(doctor, DoctorDTO.class);

        dto.setDepartmentId(
                doctor.getDepartment().getDepartmentId());

        dto.setDepartmentName(
                doctor.getDepartment().getDepartmentName());

        return dto;

    }

    @Override
    public DoctorDTO updateDoctor(Long id,
            DoctorDTO dto) {

        Doctor doctor = doctorRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Doctor not found"));

        Department department = departmentRepository
                .findById(dto.getDepartmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found"));

        doctor.setDoctorName(dto.getDoctorName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setQualification(dto.getQualification());
        doctor.setExperience(dto.getExperience());
        doctor.setEmail(dto.getEmail());
        doctor.setMobile(dto.getMobile());
        doctor.setDepartment(department);

        Doctor updatedDoctor =
                doctorRepository.save(doctor);

        DoctorDTO response =
                modelMapper.map(updatedDoctor, DoctorDTO.class);

        response.setDepartmentId(department.getDepartmentId());
        response.setDepartmentName(department.getDepartmentName());

        return response;

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