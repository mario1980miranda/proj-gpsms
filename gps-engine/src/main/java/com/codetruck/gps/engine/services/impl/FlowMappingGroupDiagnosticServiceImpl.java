package com.codetruck.gps.engine.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codetruck.gps.engine.models.FlowMappingActionResponseModel;
import com.codetruck.gps.engine.models.FlowMappingGroupDiagnosticModel;
import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.repositories.ActionRepository;
import com.codetruck.gps.engine.repositories.FlowMappingActionResponseRepository;
import com.codetruck.gps.engine.repositories.FlowMappingGroupDiagnosticRepository;
import com.codetruck.gps.engine.repositories.GroupDiagnosticRepository;
import com.codetruck.gps.engine.repositories.ServiceResultRepository;
import com.codetruck.gps.engine.services.FlowMappingGroupDiagnosticService;

@Service
public class FlowMappingGroupDiagnosticServiceImpl implements FlowMappingGroupDiagnosticService {

	final FlowMappingGroupDiagnosticRepository flowMappingGroupDiagnosticRepository;
	final GroupDiagnosticRepository groupDiagnosticRepository;
	final ServiceResultRepository serviceResultRepository;
	final FlowMappingActionResponseRepository flowMappingActionResponseRepository;
	final ActionRepository actionRepository;

	public FlowMappingGroupDiagnosticServiceImpl(
			FlowMappingGroupDiagnosticRepository diagnosticOrServiceResultRepository,
			GroupDiagnosticRepository groupDiagnosticRepository, ServiceResultRepository serviceResultRepository,
			FlowMappingActionResponseRepository actionResponseRepository, ActionRepository actionRepository
	) {
		this.flowMappingGroupDiagnosticRepository = diagnosticOrServiceResultRepository;
		this.groupDiagnosticRepository = groupDiagnosticRepository;
		this.serviceResultRepository = serviceResultRepository;
		this.flowMappingActionResponseRepository = actionResponseRepository;
		this.actionRepository = actionRepository;
	}

	@Override
	public List<FlowMappingGroupDiagnosticModel> findByDiagnosticCurrent(GroupDiagnosticModel groupDiagnosticModel) {
		return this.flowMappingGroupDiagnosticRepository.findByGroupDiagnosticModelCurrent(groupDiagnosticModel);
	}

	@Transactional
	@Override
	public void saveMapGroupDiagnosticAndMapActionResponse(
			FlowMappingGroupDiagnosticModel flowMappingGroupDiagnosticModel,
			FlowMappingActionResponseModel flowMappingActionResponseModel) {
		
		this.flowMappingGroupDiagnosticRepository.save(flowMappingGroupDiagnosticModel);
		this.flowMappingActionResponseRepository.save(flowMappingActionResponseModel);
	}

}
