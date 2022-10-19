package com.codetruck.gps.engine.dtos;

import java.util.UUID;

import com.codetruck.gps.engine.enums.ActionResponse;

import lombok.Data;

@Data
public class MapNextGroupDiagnosticOrServiceResultSearchDto {

	private UUID id_RL_GD_RS_GD;
	
	private UUID id_GD_ATUAL;
	
	private UUID id_GD_PROX;
	
	private UUID id_RES_ATEND;
		
	private UUID actionId;
	
	private ActionResponse actionResponse;
}
