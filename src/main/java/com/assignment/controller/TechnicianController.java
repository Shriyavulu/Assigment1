package com.assignment.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.dto.TechnicianDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Technician;
import com.assignment.service.TechnicianService;

@RestController
@RequestMapping("/technician")
public class TechnicianController {

	private final TechnicianService technicianService;

	public TechnicianController(TechnicianService technicianService) {
		this.technicianService = technicianService;
	}

	@PostMapping
	public ResponseEntity<Technician> createTechnician(@Valid @RequestBody TechnicianDto technicianDto)
			throws AssignmentException {
		return ResponseEntity.status(HttpStatus.CREATED.value())
				.body(technicianService.createTechnician(technicianDto));
	}

	@PutMapping("{id}")
	public ResponseEntity<Technician> updateTechnician(@PathVariable Long id,
			@Valid @RequestBody TechnicianDto technicianDto) throws AssignmentException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(technicianService.updateTechnician(id, technicianDto));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTechnician(@PathVariable Long id) throws AssignmentException {
		return ResponseEntity.status(HttpStatus.OK.value()).body(technicianService.deleteTechnician(id));
	}

}
