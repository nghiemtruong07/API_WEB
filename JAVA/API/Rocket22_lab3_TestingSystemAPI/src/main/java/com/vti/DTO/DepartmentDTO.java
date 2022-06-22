package com.vti.DTO;

public class DepartmentDTO {
	String name;
	short id;
	
	
	
	
	
	public DepartmentDTO() {
		
	}

	public DepartmentDTO(String name, short id) {
		super();
		this.name = name;
		this.id = id;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
