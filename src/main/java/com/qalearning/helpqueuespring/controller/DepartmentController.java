package com.qalearning.helpqueuespring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.qalearning.helpqueuespring.entity.Department;
import com.qalearning.helpqueuespring.repository.DepartmentRepository;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@GetMapping(path = "/all")
	public ResponseEntity<List<Department>> getAllDepartments() {
		return ResponseEntity.ok(departmentRepository.findAll());
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Department> addDepartment(@RequestBody Department newDepartment) {
		try {
			Department createdDepartment = departmentRepository.save(newDepartment);
			return new ResponseEntity<>(createdDepartment, HttpStatus.CREATED);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to add new department", exc);
		}
	}

}
