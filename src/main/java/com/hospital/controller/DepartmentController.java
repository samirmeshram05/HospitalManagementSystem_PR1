package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.DepartmentDTO;
import com.hospital.entity.Department;
import com.hospital.service.DepartmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	/*
	 * @PostMapping public Department saveDepartment(@Valid @RequestBody Department
	 * department) {
	 * 
	 * System.out.println(department);
	 * 
	 * return departmentService.saveDepartment(department); }
	 */

	@PostMapping
	public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO departmentDTO) {
		return departmentService.saveDepartment(departmentDTO);
	}

	@GetMapping
	public List<DepartmentDTO> getAllDepartments() {
		return departmentService.getAllDepartments();
	}

	@GetMapping("/{id}")
	public DepartmentDTO getDepartmentById(@PathVariable Long id) {
		return departmentService.getDepartmentById(id);
	}

	@PutMapping("/{id}")
	public DepartmentDTO updateDepartment( @PathVariable Long id, @Valid @RequestBody DepartmentDTO dto) {
		return departmentService.updateDepartment(id, dto);
	}

	@DeleteMapping("/{id}")
	public String deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return "Department Deleted Successfully";
	}

}