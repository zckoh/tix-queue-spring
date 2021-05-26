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

import com.qalearning.helpqueuespring.entity.Technician;
import com.qalearning.helpqueuespring.repository.TechnicianRepository;

@RestController
@RequestMapping(path = "/technician")
public class TechnicianController {

	@Autowired
	private TechnicianRepository technicianRepository;

	@GetMapping(path = "/all")
	public ResponseEntity<List<Technician>> getAllTechnicians() {
		return ResponseEntity.ok(technicianRepository.findAll());
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Technician> addTechnician(@RequestBody Technician newTechnician) {
		try {
			Technician createdTechnician = technicianRepository.save(newTechnician);
			return new ResponseEntity<>(createdTechnician, HttpStatus.CREATED);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to add new technician", exc);
		}
	}

}
