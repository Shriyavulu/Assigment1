package com.assignment.service;

import javax.validation.Valid;

import com.assignment.dto.TechnicianDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Technician;

public interface TechnicianService {

	Technician createTechnician(@Valid TechnicianDto technicianDto) throws AssignmentException;

	Technician updateTechnician(Long id, @Valid TechnicianDto technicianDto) throws AssignmentException;;

	String deleteTechnician(Long id) throws AssignmentException;

}
