package com.codetruck.gps.engine.dtos;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import lombok.Data;

@Data
public class MapNextGroupDiagnosticOrServiceResultDto {
	
	private UUID id_RL_GD_RS_GD;
	
	private UUID id_GD_ATUAL;
	
	private UUID id_GD_PROX;
	
	private UUID id_RES_ATEND;
	
	private Set<MapGroupDiagnosticActionResponseDto> actionResponseMapping = new HashSet<>();
}
