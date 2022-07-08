package com.assignment.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dto.AssignmentDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Assignment;
import com.assignment.service.AssignmentService;

@RestController
@RequestMapping("/assignment")
public class AssignmentController {

	private final AssignmentService assignmentService;

	public AssignmentController(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	@PutMapping("{id}")
	public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id,
			@Valid @RequestBody AssignmentDto assignmentDto) throws AssignmentException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(assignmentService.updateAssignment(id, assignmentDto));
	}

	@GetMapping("{id}")
	public ResponseEntity<Assignment> getAssignment(@PathVariable Long id) throws AssignmentException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(assignmentService.getAssignment(id));
	}

}
