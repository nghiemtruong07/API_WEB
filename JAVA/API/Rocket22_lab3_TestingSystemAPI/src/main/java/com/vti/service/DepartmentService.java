package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vti.entity.Department;
import com.vti.form.DepartmentformforCreating;
import com.vti.repository.IDepartmentRepository;

@Service
public class DepartmentService implements IDepartmentService {
	@Autowired
	private IDepartmentRepository departmentRepository;

	public List<Department> getAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public Department getDepartmentById(short idInput) {
		return departmentRepository.findById(idInput).get();
	}

	@Override
	public Department getDepartmentByName(String nameInput) {
//		return departmentRepository.findAll();
		return departmentRepository.findByName(nameInput);
	}

	@Override
	public void createNewDepartment(DepartmentformforCreating departmentformforCreating) {
		Department department = new Department();
		department.setName(departmentformforCreating.getName());
	
		departmentRepository.save(department);
	}
}
