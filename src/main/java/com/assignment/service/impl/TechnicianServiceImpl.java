package com.assignment.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.assignment.dao.TechnicianDao;
import com.assignment.dto.TechnicianDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Technician;
import com.assignment.service.TechnicianService;

@Service
public class TechnicianServiceImpl implements TechnicianService {

	private final TechnicianDao technicianDao;

	private final ModelMapper modelMapper;

	private static final String TECHNICIAN_NOT_EXISTS_IN_DB = "Technician is not present in DB with id  :";

	private static final String TECHNICIAN_ALREADY_REGISTERD = "Technician is already registered with email id  :";

	public TechnicianServiceImpl(TechnicianDao technicianDao, ModelMapper modelMapper) {
		this.technicianDao = technicianDao;
		this.modelMapper = modelMapper;
	}

	@Override
	public Technician createTechnician(@Valid TechnicianDto technicianDto) throws AssignmentException {
		Technician existingTechnician = technicianDao.findByEmail(technicianDto.getEmail());
		if (Objects.nonNull(existingTechnician)) {
			throw new AssignmentException(HttpStatus.CONFLICT, TECHNICIAN_ALREADY_REGISTERD + technicianDto.getEmail());
		}
		Technician technician = modelMapper.map(technicianDto, Technician.class);
		return technicianDao.save(technician);
	}

	@Override
	public Technician updateTechnician(Long id, @Valid TechnicianDto technicianDto) throws AssignmentException {
		Optional<Technician> technician = technicianDao.findById(id);
		if (!technician.isPresent()) {
			throw new AssignmentException(HttpStatus.NOT_FOUND, TECHNICIAN_NOT_EXISTS_IN_DB + id);
		}
		Technician tech = technician.get();
		tech.setFirstName(technicianDto.getFirstName());
		tech.setLastName(technicianDto.getLastName());
		return technicianDao.save(tech);
	}

	@Override
	public String deleteTechnician(Long id) throws AssignmentException {
		Optional<Technician> technician = technicianDao.findById(id);
		if (!technician.isPresent()) {
			throw new AssignmentException(HttpStatus.NOT_FOUND, TECHNICIAN_NOT_EXISTS_IN_DB + id);
		}
		technicianDao.delete(technician.get());
		return "Deleted Successfully.";
	}

}
