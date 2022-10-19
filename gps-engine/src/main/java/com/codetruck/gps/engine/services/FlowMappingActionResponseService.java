package com.codetruck.gps.engine.services;

import java.util.Optional;
import java.util.UUID;

import com.codetruck.gps.engine.models.FlowMappingActionResponseModel;

public interface FlowMappingActionResponseService {

	Optional<FlowMappingActionResponseModel> findFlowByMappingGroupDiagnosticId(UUID mapId);

}
