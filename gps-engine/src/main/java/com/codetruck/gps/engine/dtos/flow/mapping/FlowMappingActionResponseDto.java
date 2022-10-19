package com.codetruck.gps.engine.dtos.flow.mapping;

import java.util.UUID;

import com.codetruck.gps.engine.enums.ActionResponseEnum;

import lombok.Data;

@Data
public class FlowMappingActionResponseDto {

	private UUID id_RL_GD_RS_GD;
	
	private UUID actionId;
	
	private ActionResponseEnum actionResponse;
}
