package com.qalearning.helpqueuespring.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
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
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class StatusControllerIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper jsonConverter;

	@Test
	void getAllStatusTest() throws Exception {
		Status newStatus = new Status("New");
		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonConverter.writeValueAsString(newStatus)));

		
		Status inProgressStatus = new Status(2, "In Progress");
		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonConverter.writeValueAsString(inProgressStatus)));

		Status completedStatus = new Status();
		completedStatus.setName("completed");
		completedStatus.setId(3);
		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonConverter.writeValueAsString(completedStatus)));

		newStatus.setId(1);
		List<Status> allStatusList = List.of(newStatus, inProgressStatus, completedStatus);
		this.mvc.perform(get("/status/all").accept(MediaType.APPLICATION_JSON))

				.andExpect(status().isOk())
				.andExpect(content().json(this.jsonConverter.writeValueAsString(allStatusList)));

	}

	@Test
	void addStatusTest() throws Exception {
		Status expectedStatus = new Status(4, "The Last");

		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"The Last\"}"))

				.andExpect(status().isCreated())
				.andExpect(content().json(this.jsonConverter.writeValueAsString(expectedStatus)));
	}

	@Test
	void addWrongStatusTest() throws Exception {
		this.mvc.perform(post("/status/add").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
				.content("{\"wrongProperty\": \"Wrong value\"}"))

				.andExpect(status().isBadRequest());
	}
}
