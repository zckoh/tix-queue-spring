package com.qalearning.helpqueuespring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String title;

	private String author;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(columnDefinition = "TEXT")
	private String solution;

	@CreationTimestamp
	private LocalDateTime createdAt;

	@OneToOne
	@JoinColumn(name = "status_id", nullable = false)
	private Status status;

	@OneToOne
	@JoinColumn(name = "department_id")
	private Department department;

	@OneToOne
	@JoinColumn(name = "technician_id")
	private Technician technician;

	public Ticket() {
	}

	public Ticket(String title, String author, String description, LocalDateTime createdAt, Status status) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.createdAt = createdAt;
		this.status = status;
	}

	public Ticket(String title, String author, String description, String solution, LocalDateTime createdAt,
			Status status, Department department) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.solution = solution;
		this.createdAt = createdAt;
		this.status = status;
		this.department = department;
	}

	public Ticket(String title, String author, String description, String solution, LocalDateTime createdAt,
			Status status, Department department, Technician technician) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.solution = solution;
		this.createdAt = createdAt;
		this.status = status;
		this.department = department;
		this.technician = technician;
	}

	public Ticket(Integer id, String title, String author, String description, LocalDateTime createdAt, Status status) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.createdAt = createdAt;
		this.status = status;
	}

	public Ticket(Integer id, String title, String author, String description, LocalDateTime createdAt, Status status,
			String solution) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.createdAt = createdAt;
		this.status = status;
		this.solution = solution;
	}

	public Ticket(Integer id, String title, String author, String description, String solution, LocalDateTime createdAt,
			Status status, Department department) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.solution = solution;
		this.createdAt = createdAt;
		this.status = status;
		this.department = department;
	}

	public Ticket(Integer id, String title, String author, String description, String solution, LocalDateTime createdAt,
			Status status, Department department, Technician technician) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.solution = solution;
		this.createdAt = createdAt;
		this.status = status;
		this.department = department;
		this.technician = technician;
	}

	public Integer getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Technician gettechnician() {
		return technician;
	}

	public void settechnician(Technician technician) {
		this.technician = technician;
	}

}