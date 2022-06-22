package com.vti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.DTO.DepartmentDTO;
import com.vti.entity.Department;
import com.vti.form.DepartmentformforCreating;
import com.vti.service.IDepartmentService;

@RestController
@RequestMapping(value = "api/v1/departments")
@CrossOrigin("*")
public class DepartmentController {

	@Autowired
	private IDepartmentService departmentService;
	
	@GetMapping
	public ResponseEntity<?> getAllDepartment() {
		List<Department> lisDepartment_DB = departmentService.getAllDepartment();
		List<DepartmentDTO> departmentDTOs = new ArrayList<>();
		for (Department department : lisDepartment_DB) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
			departmentDTOs.add(departmentDTO);
		}
				return new ResponseEntity<>(departmentDTOs,HttpStatus.OK);
	}
	
	//get department by ID
	@GetMapping("/{id}")
	public ResponseEntity<?> getDepartmentById(@PathVariable(name = "id") short idInput) {
		Department department = departmentService.getDepartmentById(idInput);

			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setId(department.getId());
			departmentDTO.setName(department.getName());
				return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
	}
//	get department by Name
//	@GetMapping("/{name}")
//	public ResponseEntity<?> getDepartmentByName(@PathVariable(name = "name") String nameInput) {
//		Department department = departmentService.getDepartmentByName(nameInput);
//
//			DepartmentDTO departmentDTO = new DepartmentDTO();
//			departmentDTO.setId(department.getId());
//			departmentDTO.setName(department.getName());
//				return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
//	}
	@PostMapping()
	public ResponseEntity<?> createNewDepartment(@RequestBody DepartmentformforCreating departmentformforCreating){
		departmentService.createNewDepartment(departmentformforCreating);
				return new ResponseEntity<>("Create department success: ",HttpStatus.CREATED);
	}
	
	//get department by Name
		@GetMapping("/name{name}")
		public ResponseEntity<?> getDepartmentByName(@PathVariable(name = "name") String depName) {
			Department department = departmentService.getDepartmentByName(depName);

				DepartmentDTO departmentDTO = new DepartmentDTO();
				departmentDTO.setId(department.getId());
				departmentDTO.setName(department.getName());
					return new ResponseEntity<>(departmentDTO,HttpStatus.OK);
		}
}
