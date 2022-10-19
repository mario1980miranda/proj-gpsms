package com.codetruck.gps.engine.dtos.flow.mapping;

import java.util.UUID;

import lombok.Data;

@Data
public class FlowMappingGroupDiagnosticDto {
	
	private UUID flowMappingGroupDiagnosticId;
	
	private UUID groupdDiagnosticCurrentId;
	
	private UUID groupdDiagnosticNextId;
	
	private UUID serviceResultId;
	
	private FlowMappingActionResponseDto flowMappingActionResponseDto;
}
