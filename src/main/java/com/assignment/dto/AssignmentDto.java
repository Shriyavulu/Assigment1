package com.assignment.dto;

import java.time.OffsetDateTime;

import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AssignmentDto {

	@NotNull
	private OffsetDateTime startTime;

	@NotNull
	private OffsetDateTime endTime;

	@NotNull
	private Long technicianId;

}
