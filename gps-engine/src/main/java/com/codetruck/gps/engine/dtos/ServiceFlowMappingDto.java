package com.codetruck.gps.engine.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class ServiceFlowMappingDto {

	private UUID idGroupDiagnostic;
	
	private Set<MapNextGroupDiagnosticOrServiceResultDto> flows = new HashSet<>();
}
