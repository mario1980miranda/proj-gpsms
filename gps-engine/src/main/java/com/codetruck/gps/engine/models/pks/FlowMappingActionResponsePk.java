package com.codetruck.gps.engine.models.pks;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.IdClass;

import com.codetruck.gps.engine.enums.ActionResponseEnum;

import lombok.Data;

@Data
@IdClass(FlowMappingActionResponsePk.class)
public class FlowMappingActionResponsePk implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID flowMappingGroupDiagnosticId;
	
	private UUID actionId;
	
	private ActionResponseEnum actionResponse;
	
}
