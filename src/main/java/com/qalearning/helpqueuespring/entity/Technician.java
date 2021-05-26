package com.qalearning.helpqueuespring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Technician {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@OneToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Technician() {
		super();
	}

	public Technician(String name) {
		super();
		this.name = name;
	}

	public Technician(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Technician(String name, Department department) {
		super();
		this.name = name;
		this.department = department;
	}

	public Technician(Integer id, String name, Department department) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
	}

}
