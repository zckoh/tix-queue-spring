package com.qalearning.helpqueuespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qalearning.helpqueuespring.entity.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {

}