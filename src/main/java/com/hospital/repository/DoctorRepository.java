package com.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);
    
    List<Doctor> findBySpecialization( String specialization );
    
    List<Doctor> findByDoctorNameContainingIgnoreCase(String doctorName);

    List<Doctor> findBySpecializationContainingIgnoreCase(String specialization);

    List<Doctor> findByDepartmentDepartmentNameContainingIgnoreCase(String departmentName);

}