package com.assignment.service;

import javax.validation.Valid;

import com.assignment.dto.AssignmentDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Assignment;

public interface AssignmentService {

	Assignment updateAssignment(Long id, @Valid AssignmentDto assignmentDto) throws AssignmentException;

	Assignment getAssignment(Long id) throws AssignmentException;

}
