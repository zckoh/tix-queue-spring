package com.qalearning.helpqueuespring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qalearning.helpqueuespring.entity.Status;

@ContextConfiguration
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TicketControllerIntegrationTest {

	@Autowired

	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonConverter;

	@BeforeAll
	void setUp() throws Exception {
		Status newStatus = new Status(1, "New");
		Status inProgressStatus = new Status(2, "In Progress");
		Status completedStatus = new Status(3, "Completed");

		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonConverter.writeValueAsString(newStatus)));

		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonConverter.writeValueAsString(inProgressStatus)));

		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonConverter.writeValueAsString(completedStatus)));
	}

	@Test
	void addNewTicketTest() throws Exception {
		String ticketJSONInput = "{ \"title\": \"Test add new ticket\", " + "\"author\": \"example@test.com\","
				+ " \"description\": \"This is a test ticket\", " + "\"status\": { \"id\": 1, \"name\": \"New\" } }";

		this.mvc.perform(post("/ticket/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ticketJSONInput)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.title").value("Test add new ticket")).andReturn();
	}

	@Test
	void addInvalidTicketTest() throws Exception {
		String invalidTicketJSONInput = "{ \"title\": \"Test add another ticket\", "
				+ "\"author\": \"example@test.com\"," + " \"description\": \"This is a test ticket\"" + " }";

		this.mvc.perform(post("/ticket/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(invalidTicketJSONInput)).andExpect(status().isBadRequest());
	}

	@Test
	void deleteTicketById() throws Exception {
		// Create a ticket to be deleted
		String ticketToBeDeleted = "{ \"title\": \"Ticket to be deleted\", " + "\"author\": \"example@test.com\","
				+ " \"description\": \"This is a test ticket\", " + "\"status\": { \"id\": 1, \"name\": \"New\" } }";

		this.mvc.perform(post("/ticket/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(ticketToBeDeleted)).andExpect(status().isCreated());

		// Delete the ticket
		this.mvc.perform(delete("/ticket/delete/2")).andExpect(status().isOk())
				.andExpect(content().string("Ticket with id - 2 is deleted"));
	}

	@Test
	void updateTicketTest() throws Exception {
		// Create a ticket to be updated
//		String ticketJSONInput = "{ \"title\": \"Ticket to be updated\", " + "\"author\": \"example@test.com\","
//				+ " \"description\": \"This is a test ticket\", " + "\"status\": { \"id\": 1, \"name\": \"New\" } }";
//
//		MvcResult result = this.mvc.perform(post("/ticket/add").accept(MediaType.APPLICATION_JSON)
//				.contentType(MediaType.APPLICATION_JSON).content(ticketJSONInput)).andExpect(status().isCreated())
//				.andReturn();
//
//		Ticket ticketToBeUpdated = new ObjectMapper().readValue(result.getResponse().getContentAsString(),
//				Ticket.class);
//		// Update ticket title
//		ticketToBeUpdated.setTitle("Updated ticket title");
//
//		this.mvc.perform(
//				post("/ticket/update").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
//						.content(this.jsonConverter.writeValueAsString(ticketToBeUpdated)))
//				.andExpect(status().isAccepted());

	}

	@Test
	void updatedInvalidTicketTest() {

	}

	@Test
	void updatedNonExistingTicketTest() {

	}
}
