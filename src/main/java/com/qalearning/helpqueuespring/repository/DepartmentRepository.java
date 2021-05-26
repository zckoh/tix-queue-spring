package com.qalearning.helpqueuespring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.qalearning.helpqueuespring.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
