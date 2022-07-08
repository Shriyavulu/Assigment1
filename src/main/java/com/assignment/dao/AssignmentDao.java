package com.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Assignment;

@Repository
public interface AssignmentDao extends JpaRepository<Assignment, Long> {

}
