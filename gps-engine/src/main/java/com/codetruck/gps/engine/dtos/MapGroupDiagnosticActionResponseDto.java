package com.codetruck.gps.engine.dtos;

import java.util.UUID;

import com.codetruck.gps.engine.enums.ActionResponse;

import lombok.Data;

@Data
public class MapGroupDiagnosticActionResponseDto {

	private UUID id_RL_GD_RS_GD;
	
	private UUID actionId;
	
	private ActionResponse actionResponse;
}
