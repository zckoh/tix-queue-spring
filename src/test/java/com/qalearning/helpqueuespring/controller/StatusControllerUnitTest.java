package com.qalearning.helpqueuespring.controller;

import java.util.List;

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
import com.qalearning.helpqueuespring.repository.StatusRepository;

@ContextConfiguration
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
class StatusControllerUnitTest {

	@Autowired
	private StatusController controller;

	@MockBean
	private StatusRepository repo;

	@Test
	void testGetAllStatus() {
		// GIVEN
		Status tester = new Status("New");
		List<Status> testerList = List.of(tester);
		ResponseEntity<List<Status>> expected = new ResponseEntity<List<Status>>(testerList, HttpStatus.OK);

		// WHEN
		Mockito.when(this.repo.findAll())
				// THEN
				.thenReturn(testerList);

		// ASSET
		Assertions.assertThat(this.controller.getAllStatus()).isEqualTo(expected);

		// VERIFY
		Mockito.verify(this.repo, Mockito.times(1)).findAll();

		// ASSERT - actual result should = expected result
		// VERIFY - verify that the service tried talking to the repository
	}

}
