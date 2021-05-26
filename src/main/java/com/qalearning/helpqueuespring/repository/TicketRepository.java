package com.qalearning.helpqueuespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qalearning.helpqueuespring.entity.Ticket;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}