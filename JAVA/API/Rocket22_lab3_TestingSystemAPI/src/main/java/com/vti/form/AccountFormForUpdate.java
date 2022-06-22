package com.vti.form;

public class AccountFormForUpdate {
	String fullname;
	short departmentId;
	short positionId;
	String username;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AccountFormForUpdate() {
		super();
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public short getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(short departmentId) {
		this.departmentId = departmentId;
	}

	public short getPositionId() {
		return positionId;
	}

	public void setPositionId(short positionId) {
		this.positionId = positionId;
	}

}
