package com.assignment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.dao.TechnicianDao;
import com.assignment.dto.TechnicianDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Technician;

@RunWith(SpringJUnit4ClassRunner.class)
public class TechnicianServiceImplTest {

	@Mock
	private TechnicianDao technicianDao;

	@Mock
	private ModelMapper modelMapper;

	@InjectMocks
	private TechnicianServiceImpl technicianServiceImpl;

	private final static Long ID = 1L;
	private final static String FIRST_NAME = "John";
	private final static String LAST_NAME = "Smith";
	private final static String EMAIL = "john.smith@gmail.com";

	@Test(expected = AssignmentException.class)
	public void createTechnician_ShouldThrowAssignmentException() throws AssignmentException {
		Mockito.when(technicianDao.findByEmail(EMAIL)).thenReturn(buildTechnician());
		technicianServiceImpl.createTechnician(buildTechnicianDto());
	}

	@Test
	public void createTechnician_Success() throws AssignmentException {
		Mockito.when(technicianDao.findByEmail(EMAIL)).thenReturn(null);
		Mockito.when(technicianDao.save(buildTechnician())).thenReturn(buildTechnician());
		Mockito.when(modelMapper.map(org.mockito.ArgumentMatchers.any(), org.mockito.ArgumentMatchers.any()))
				.thenReturn(buildTechnician());
		Technician technicianResponse = technicianServiceImpl.createTechnician(buildTechnicianDto());
		Assert.assertNotNull(technicianResponse);
		assertEquals(ID, technicianResponse.getId());
		assertEquals(FIRST_NAME, technicianResponse.getFirstName());
		assertEquals(LAST_NAME, technicianResponse.getLastName());
		assertEquals(EMAIL, technicianResponse.getEmail());
	}

	@Test
	public void updateTechnician_Success() throws AssignmentException {
		Mockito.when(technicianDao.findById(ID)).thenReturn(Optional.of(buildTechnician()));
		Mockito.when(technicianDao.save(buildTechnician())).thenReturn(buildTechnician());
		Technician technicianResponse = technicianServiceImpl.updateTechnician(ID, buildTechnicianDto());
		Assert.assertNotNull(technicianResponse);
		assertEquals(ID, technicianResponse.getId());
		assertEquals(FIRST_NAME, technicianResponse.getFirstName());
		assertEquals(LAST_NAME, technicianResponse.getLastName());
		assertEquals(EMAIL, technicianResponse.getEmail());
	}

	@Test(expected = AssignmentException.class)
	public void updateTechnician_ShouldThrowAssignmentException() throws AssignmentException {
		Mockito.when(technicianDao.findById(ID)).thenReturn(Optional.empty());
		technicianServiceImpl.updateTechnician(ID, buildTechnicianDto());

	}

	@Test
	public void deleteTechnician_Success() throws AssignmentException {
		Mockito.when(technicianDao.findById(ID)).thenReturn(Optional.of(buildTechnician()));
		String technicianResponse = technicianServiceImpl.deleteTechnician(ID);
		assertEquals("Deleted Successfully.", technicianResponse);
	}

	@Test(expected = AssignmentException.class)
	public void deleteTechnician_ShouldThrowAssignmentException() throws AssignmentException {
		Mockito.when(technicianDao.findById(ID)).thenReturn(Optional.empty());
		technicianServiceImpl.deleteTechnician(ID);
	}

	private Technician buildTechnician() {
		return Technician.builder().id(ID).firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL).build();
	}

	private TechnicianDto buildTechnicianDto() {
		return TechnicianDto.builder().firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL).build();
	}

}
