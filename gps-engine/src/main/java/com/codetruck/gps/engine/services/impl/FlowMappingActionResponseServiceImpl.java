package com.codetruck.gps.engine.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.codetruck.gps.engine.models.FlowMappingActionResponseModel;
import com.codetruck.gps.engine.repositories.FlowMappingActionResponseRepository;
import com.codetruck.gps.engine.services.FlowMappingActionResponseService;

@Service
public class FlowMappingActionResponseServiceImpl implements FlowMappingActionResponseService {

	private FlowMappingActionResponseRepository mapGroupDiagnosticActionResponseRepository;
	
	public FlowMappingActionResponseServiceImpl(
			FlowMappingActionResponseRepository mapGroupDiagnosticActionResponseRepository
	) {
		
		this.mapGroupDiagnosticActionResponseRepository = mapGroupDiagnosticActionResponseRepository;
	}



	@Override
	public Optional<FlowMappingActionResponseModel> findFlowByMappingGroupDiagnosticId(UUID mapId) {
		return this.mapGroupDiagnosticActionResponseRepository.findFlowByMappingGroupDiagnosticId(mapId);
	}

}
