package com.qalearning.helpqueuespring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qalearning.helpqueuespring.entity.Ticket;
import com.qalearning.helpqueuespring.repository.TicketRepository;

@RestController
@RequestMapping(path = "/ticket")
public class TicketController {
	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping(path = "/{id}", produces = "application/json")
	public ResponseEntity<Ticket> getTicketById(@PathVariable Integer id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		return ResponseEntity.ok(ticket.get());
	}

	@GetMapping(path = "/readAll", produces = "application/json")
	public ResponseEntity<List<Ticket>> getAllTickets() {
		return ResponseEntity.ok(ticketRepository.findAll());
	}

	@PostMapping(path = "/add")
	public ResponseEntity<Ticket> addNewTicket(@RequestBody Ticket newTicket) {
		try {
			Ticket createdTicket = ticketRepository.save(newTicket);
			return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to add new ticket", exc);
		}
	}

	@PostMapping(path = "/update")
	public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticketToUpdate) {
		try {
			Optional<Ticket> ticketOptional = ticketRepository.findById(ticketToUpdate.getId());

			if (!ticketOptional.isPresent())
				return ResponseEntity.notFound().build();

			Ticket updatedTicket = ticketRepository.save(ticketToUpdate);

			return new ResponseEntity<>(updatedTicket, HttpStatus.ACCEPTED);
		} catch (Exception exc) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unable to update ticket", exc);
		}
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<String> deleteTicketById(@PathVariable Integer id) {
		try {
			ticketRepository.deleteById(id);
			return ResponseEntity.ok("Ticket with id - " + id + " is deleted");
		} catch (EmptyResultDataAccessException exc) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to delete ticket", exc);
		}
	}
}