package com.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hospital.dto.DepartmentDTO;
import com.hospital.service.DepartmentService;
import com.hospital.util.ApiResponse;

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
	public ApiResponse<DepartmentDTO> saveDepartment(@Valid @RequestBody DepartmentDTO dto) {
		DepartmentDTO savedDepartment = departmentService.saveDepartment(dto);
		return new ApiResponse<>(true, "Department Saved Successfully", savedDepartment);

	}

	/*
	 * @GetMapping public List<DepartmentDTO> getAllDepartments() { return
	 * departmentService.getAllDepartments(); }
	 */
	@GetMapping
	public ApiResponse<List<DepartmentDTO>> getAllDepartments() {
		return new ApiResponse<>(true, "Department List", departmentService.getAllDepartments());

	}

	/*
	 * @GetMapping("/{id}") public DepartmentDTO getDepartmentById(@PathVariable
	 * Long id) { return departmentService.getDepartmentById(id); }
	 */

	@GetMapping("/{id}")
	public ApiResponse<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
		return new ApiResponse<>(
				true,
				"Department Found",
				departmentService.getDepartmentById(id));
	}

	/*
	 * @PutMapping("/{id}") public DepartmentDTO updateDepartment(@PathVariable Long
	 * id, @Valid @RequestBody DepartmentDTO dto) { return
	 * departmentService.updateDepartment(id, dto); }
	 */

	@PutMapping("/{id}")
	public ApiResponse<DepartmentDTO> updateDepartment(
			@PathVariable Long id,
			@RequestBody DepartmentDTO dto) {
		return new ApiResponse<>(
				true,
				"Department Updated Successfully",
				departmentService.updateDepartment(id, dto));
	}

	/*
	 * @DeleteMapping("/{id}") public String deleteDepartment(@PathVariable Long id)
	 * { departmentService.deleteDepartment(id); return
	 * "Department Deleted Successfully"; }
	 * 
	 */

	@DeleteMapping("/{id}")
	public ApiResponse<String> deleteDepartment(@PathVariable Long id) {
		departmentService.deleteDepartment(id);
		return new ApiResponse<>(
				true,
				"Department Deleted Successfully",
				null);
	}

}