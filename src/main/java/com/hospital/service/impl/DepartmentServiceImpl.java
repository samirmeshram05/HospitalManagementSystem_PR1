package com.hospital.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.dto.DepartmentDTO;
import com.hospital.entity.Department;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DepartmentRepository;
import com.hospital.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private ModelMapper modelMapper;

   /* @Override
    public Department saveDepartment(Department department) {

        if (departmentRepository.existsByDepartmentCode(department.getDepartmentCode())) {
            throw new RuntimeException("Department Code already exists.");
        }

        if (departmentRepository.existsByDepartmentName(department.getDepartmentName())) {
            throw new RuntimeException("Department Name already exists.");
        }

        return departmentRepository.save(department);
    }*/
    
    @Override
    public DepartmentDTO saveDepartment(DepartmentDTO dto) {

        if (departmentRepository.existsByDepartmentCode(dto.getDepartmentCode())) {
            throw new RuntimeException("Department Code already exists.");
        }

        if (departmentRepository.existsByDepartmentName(dto.getDepartmentName())) {
            throw new RuntimeException("Department Name already exists.");
        }

        Department department = modelMapper.map(dto, Department.class);

        Department savedDepartment = departmentRepository.save(department);

        return modelMapper.map(savedDepartment, DepartmentDTO.class);

    }

    /*
    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }*/
    
    @Override
    public List<DepartmentDTO> getAllDepartments() {

        return departmentRepository.findAll()
                .stream()
                .map(department ->
                        modelMapper.map(department, DepartmentDTO.class))
                .toList();

    }

   /* @Override
    public Department getDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with ID : " + id));
    }*/
    
    @Override
    public DepartmentDTO getDepartmentById(Long id) {

        Department department = departmentRepository.findById(id)

                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID : " + id));

        return modelMapper.map(department, DepartmentDTO.class);

    }

   /* @Override
    public Department updateDepartment(Long id, Department department) {

        Department existingDepartment = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with ID : " + id));

        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDepartmentCode(department.getDepartmentCode());
        existingDepartment.setDescription(department.getDescription());

        return departmentRepository.save(existingDepartment);
    }
    */

    @Override
    public DepartmentDTO updateDepartment(Long id,
            DepartmentDTO dto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Department not found with ID : " + id));

        department.setDepartmentName(dto.getDepartmentName());
        department.setDepartmentCode(dto.getDepartmentCode());
        department.setDescription(dto.getDescription());
        Department updatedDepartment =
                departmentRepository.save(department);

        return modelMapper.map(updatedDepartment,
                DepartmentDTO.class);

    }
    
    @Override
    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department not found with ID : " + id));

        departmentRepository.delete(department);

    }

}