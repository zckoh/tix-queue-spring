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

import com.qalearning.helpqueuespring.entity.Status;
import com.qalearning.helpqueuespring.repository.StatusRepository;

@RestController
@RequestMapping(path = "/status")
public class StatusController {
	@Autowired
	private StatusRepository statusRepository;

	@GetMapping(path = "/all")
	public ResponseEntity<List<Status>> getAllStatus() {
		return ResponseEntity.ok(statusRepository.findAll());
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<Status> addStatus(@RequestBody Status newStatus) {
		try {
			Status createdStatus = statusRepository.save(newStatus);
			return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to add new status", exc);
		}
	}
}
