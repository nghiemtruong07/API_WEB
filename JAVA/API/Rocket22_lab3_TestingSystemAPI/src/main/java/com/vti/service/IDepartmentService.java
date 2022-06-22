package com.vti.service;

import java.util.List;

import com.vti.entity.Department;
import com.vti.form.DepartmentformforCreating;

public interface IDepartmentService {
	
	public List<Department> getAllDepartment();
	
	public Department getDepartmentById(short idInput);

	public Department getDepartmentByName(String name);

	public void createNewDepartment(DepartmentformforCreating departmentformforCreating);
}
