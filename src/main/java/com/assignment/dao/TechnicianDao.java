package com.assignment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.model.Technician;

@Repository
public interface TechnicianDao extends JpaRepository<Technician, Long> {

	Technician findByEmail(String email);

}
