package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Account", catalog = "TestingSystem")
public class Account implements Serializable {
	@Column(name = "AccountID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "Email", length = 50, nullable = false, unique = true)
	private String email;

	@Column(name = "Username", length = 50, nullable = false, unique = true)
	private String username;

	@Column(name = "FullName", length = 50, nullable = false)
	private String fullName;

	@ManyToOne
	@JoinColumn(name = "DepartmentID", nullable = false)
	private Department department;

	@ManyToOne
	@JoinColumn(name = "PositionID", nullable = false)
	private Position position;

	@Column(name = "CreateDate")
	@Temporal(TemporalType.TIMESTAMP) // DATETIME
	@CreationTimestamp // DEFAULT NOW(),
	private Date createDate;
	
	@Column(name = "Password", length = 800)
	private String password;
	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
