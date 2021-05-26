package com.qalearning.helpqueuespring.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.qalearning.helpqueuespring.entity.Status;
import com.qalearning.helpqueuespring.entity.Ticket;
import com.qalearning.helpqueuespring.repository.TicketRepository;

@ContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class TicketControllerUnitTest {

	@Autowired
	private TicketController controller;

	@MockBean
	private TicketRepository repo;

	@Test
	void testAddNewTicket() {
		String title = "Test Ticket Title";
		String author = "author@test.com";
		String description = "Ticket description";
		LocalDateTime createdAt = LocalDateTime.now();
		Status status = new Status(1, "New");
		Ticket toSave = new Ticket(title, author, description, createdAt, status);

		Ticket saved = new Ticket(1, "Test Ticket Title", "author@test.com", "Ticket description", createdAt, status);

		Mockito.when(this.repo.save(toSave)).thenReturn(saved);
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(saved, HttpStatus.CREATED);
		Assertions.assertThat(this.controller.addNewTicket(toSave)).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).save(toSave);
	}

	@Test
	void testGetTicketById() {
		LocalDateTime createdAt = LocalDateTime.now();
		Status status = new Status(1, "New");
		Ticket tester = new Ticket(1, "Test Ticket Title", "author@test.com", "Ticket description", createdAt, status);
		Mockito.when(this.repo.findById(1)).thenReturn(Optional.of(tester));
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(tester, HttpStatus.OK);
		Assertions.assertThat(this.controller.getTicketById(1)).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findById(1);
	}

	@Test
	void testGetAllTickets() {
		LocalDateTime createdAt = LocalDateTime.now();
		Status status = new Status(1, "New");
		Ticket tester = new Ticket(1, "Test Ticket Title", "author@test.com", "Ticket description", createdAt, status);
		List<Ticket> testerList = List.of(tester);

		ResponseEntity<List<Ticket>> expected = new ResponseEntity<List<Ticket>>(testerList, HttpStatus.OK);
		Mockito.when(this.repo.findAll()).thenReturn(testerList);

		Assertions.assertThat(this.controller.getAllTickets()).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testUpdateTicket() {
		LocalDateTime createdAt = LocalDateTime.now();
		Status status = new Status(1, "New");
		Ticket toSave = new Ticket(1, "Test Ticket Title", "author@test.com", "Ticket description", createdAt, status);
		Ticket saved = new Ticket(1, "Test Ticket Title", "author@test.com", "Ticket description", createdAt, status);

		Mockito.when(this.repo.save(toSave)).thenReturn(saved);
		Mockito.when(this.repo.findById(toSave.getId())).thenReturn(Optional.of(saved));
		ResponseEntity<Ticket> expected = new ResponseEntity<Ticket>(saved, HttpStatus.ACCEPTED);
		Assertions.assertThat(this.controller.updateTicket(toSave)).isEqualTo(expected);
		Mockito.verify(this.repo, Mockito.times(1)).save(toSave);
	}

//	@Test
//	void testDeleteTicketById() {
//		
////		ResponseEntity<String> expected = new ResponseEntity<String>("Ticket with id - 1 is deleted", HttpStatus.OK);
////		Mockito.when(this.repo.deleteById(1)).thenReturn(expected);
//		Mockito.verify(this.repo, Mockito.time).deleteById(any()); 
//		Mockito.verify(this.repo, Mockito.times(1)).findAll();
//	}

}
