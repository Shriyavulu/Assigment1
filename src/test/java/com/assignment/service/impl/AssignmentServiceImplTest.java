package com.assignment.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.assignment.dao.AssignmentDao;
import com.assignment.dao.TechnicianDao;
import com.assignment.dto.AssignmentDto;
import com.assignment.exception.AssignmentException;
import com.assignment.model.Assignment;
import com.assignment.model.Technician;

@RunWith(SpringJUnit4ClassRunner.class)
public class AssignmentServiceImplTest {

	@Mock
	private TechnicianDao technicianDao;

	@Mock
	private AssignmentDao assignmentDao;

	@InjectMocks
	private AssignmentServiceImpl assignmentServiceImpl;

	private final static String FIRST_NAME = "John";
	private final static String LAST_NAME = "Smith";
	private final static String EMAIL = "john.smith@gmail.com";
	private final static Long ID = 1L;

	@Test
	public void getAssignment_Success() throws AssignmentException {
		Mockito.when(assignmentDao.findById(ID)).thenReturn(Optional.of(buildAssignment()));
		Assignment assignmentResponse = assignmentServiceImpl.getAssignment(ID);
		Assert.assertNotNull(assignmentResponse);
		assertEquals(ID, assignmentResponse.getId());
		assertEquals(FIRST_NAME, assignmentResponse.getTechnician().getFirstName());
		assertEquals(LAST_NAME, assignmentResponse.getTechnician().getLastName());
		assertEquals(EMAIL, assignmentResponse.getTechnician().getEmail());
	}

	@Test(expected = AssignmentException.class)
	public void getAssignment_ShouldThrowAssignmentException() throws AssignmentException {
		Mockito.when(assignmentDao.findById(ID)).thenReturn(Optional.empty());
		assignmentServiceImpl.getAssignment(ID);
	}

	@Test(expected = AssignmentException.class)
	public void updateAssignment_ShouldThrowAssignmentException() throws AssignmentException {
		Mockito.when(assignmentDao.findById(ID)).thenReturn(Optional.empty());
		assignmentServiceImpl.updateAssignment(ID, buiAssignmentDto());
	}

	@Test(expected = AssignmentException.class)
	public void updateAssignment_TechnicianNotPresent() throws AssignmentException {
		Mockito.when(assignmentDao.findById(ID)).thenReturn(Optional.of(buildAssignment()));
		Mockito.when(technicianDao.findById(ID)).thenReturn(Optional.empty());
		assignmentServiceImpl.updateAssignment(ID, buiAssignmentDto());
	}

	@Test
	public void updateAssignment_Success() throws AssignmentException {
		Mockito.when(assignmentDao.findById(ID)).thenReturn(Optional.of(buildAssignment()));
		Mockito.when(technicianDao.findById(ID)).thenReturn(Optional.of(buildTechnician()));
		Mockito.when(assignmentDao.save(Mockito.any(Assignment.class))).thenReturn(buildAssignment());
		Assignment assignmentResponse = assignmentServiceImpl.updateAssignment(ID, buiAssignmentDto());
		Assert.assertNotNull(assignmentResponse);
		assertEquals(ID, assignmentResponse.getId());
		assertEquals(FIRST_NAME, assignmentResponse.getTechnician().getFirstName());
		assertEquals(LAST_NAME, assignmentResponse.getTechnician().getLastName());
		assertEquals(EMAIL, assignmentResponse.getTechnician().getEmail());
	}

	private Technician buildTechnician() {
		return Technician.builder().id(ID).firstName(FIRST_NAME).lastName(LAST_NAME).email(EMAIL).build();
	}

	private Assignment buildAssignment() {
		return Assignment.builder().id(ID).endTime(OffsetDateTime.now()).startTime(OffsetDateTime.now())
				.technician(buildTechnician()).build();
	}

	private AssignmentDto buiAssignmentDto() {
		return AssignmentDto.builder().endTime(OffsetDateTime.now()).startTime(OffsetDateTime.now()).technicianId(ID)
				.build();
	}

}
