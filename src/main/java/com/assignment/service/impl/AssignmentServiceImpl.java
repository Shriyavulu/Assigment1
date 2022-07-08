package com.assignment.service.impl;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assignment.dao.AssignmentDao;
import com.assignment.dao.TechnicianDao;
import com.assignment.dto.AssignmentDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Assignment;
import com.assignment.model.Technician;
import com.assignment.service.AssignmentService;

@Service
public class AssignmentServiceImpl implements AssignmentService {

	private final AssignmentDao assignmentDao;

	private final TechnicianDao technicianDao;

	private static final String ASSIGNMENT_NOT_EXISTS_IN_DB = "Assignment is not present in DB with id  :";

	private static final String TECHNICIAN_NOT_EXISTS_IN_DB = "Technician is not present in DB with id  :";

	public AssignmentServiceImpl(AssignmentDao assignmentDao, TechnicianDao technicianDao) {
		this.assignmentDao = assignmentDao;
		this.technicianDao = technicianDao;
	}

	@Override
	public Assignment updateAssignment(Long id, @Valid AssignmentDto assignmentDto) throws AssignmentException {
		Optional<Assignment> assignment = assignmentDao.findById(id);
		if (!assignment.isPresent()) {
			throw new AssignmentException(HttpStatus.NOT_FOUND, ASSIGNMENT_NOT_EXISTS_IN_DB + id);
		}
		Optional<Technician> technician = technicianDao.findById(assignmentDto.getTechnicianId());
		if (!technician.isPresent()) {
			throw new AssignmentException(HttpStatus.NOT_FOUND, TECHNICIAN_NOT_EXISTS_IN_DB + id);
		}
		Assignment assign = assignment.get();
		assign.setEndTime(assignmentDto.getEndTime());
		assign.setStartTime(assignmentDto.getStartTime());
		assign.setTechnician(technician.get());
		return assignmentDao.save(assign);
	}

	@Override
	public Assignment getAssignment(Long id) throws AssignmentException {
		Optional<Assignment> assignment = assignmentDao.findById(id);
		if (!assignment.isPresent()) {
			throw new AssignmentException(HttpStatus.NOT_FOUND, ASSIGNMENT_NOT_EXISTS_IN_DB + id);
		}
		return assignment.get();
	}

}
