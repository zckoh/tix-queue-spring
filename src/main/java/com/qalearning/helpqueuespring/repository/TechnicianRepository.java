package com.qalearning.helpqueuespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qalearning.helpqueuespring.entity.Technician;

public interface TechnicianRepository extends JpaRepository<Technician, Integer> {
	
}
