package com.hospital.service;

import java.util.List;

import com.hospital.dto.DoctorDTO;
import com.hospital.entity.Doctor;

public interface DoctorService {

	DoctorDTO saveDoctor(DoctorDTO doctorDTO);

	List<DoctorDTO> getAllDoctors();

	DoctorDTO getDoctorById(Long id);

	DoctorDTO updateDoctor(Long id, DoctorDTO doctorDTO);

	List<Doctor> getAllDoctorsSorted();

	void deleteDoctor(Long id);

	List<Doctor> findBySpecialization(String specialization);

	List<Doctor> searchDoctorByName(String doctorName);

	List<Doctor> searchDoctorByDepartment(String departmentName);

	List<Doctor> searchDoctorBySpecialization(String specialization);
}