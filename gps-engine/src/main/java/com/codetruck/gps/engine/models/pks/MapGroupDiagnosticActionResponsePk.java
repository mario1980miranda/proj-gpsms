package com.codetruck.gps.engine.models.pks;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.IdClass;

import com.codetruck.gps.engine.enums.ActionResponse;

import lombok.Data;

@Data
@IdClass(MapGroupDiagnosticActionResponsePk.class)
public class MapGroupDiagnosticActionResponsePk implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID diagnosticOrServiceResultId;
	
	private UUID action;
	
	private ActionResponse response;
	
}
