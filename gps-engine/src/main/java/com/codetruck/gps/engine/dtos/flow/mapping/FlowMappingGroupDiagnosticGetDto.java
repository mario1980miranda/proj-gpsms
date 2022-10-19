package com.codetruck.gps.engine.dtos.flow.mapping;

import java.util.UUID;

import com.codetruck.gps.engine.enums.ActionResponseEnum;

import lombok.Data;

@Data
public class FlowMappingGroupDiagnosticGetDto {

	private UUID flowMappingGroupDiagnosticId;
	
	private UUID groupDiagnosticCurrentId;
	
	private UUID groupDiagnosticNextId;
	
	private UUID serviceResultId;
		
	private UUID actionId;
	
	private ActionResponseEnum actionResponse;
}
