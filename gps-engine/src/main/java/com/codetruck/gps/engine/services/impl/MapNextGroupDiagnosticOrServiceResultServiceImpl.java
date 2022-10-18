package com.codetruck.gps.engine.services.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codetruck.gps.engine.dtos.FlowServiceDto;
import com.codetruck.gps.engine.dtos.MapGroupDiagnosticActionResponseDto;
import com.codetruck.gps.engine.dtos.MapNextGroupDiagnosticOrServiceResultDto;
import com.codetruck.gps.engine.models.MapGroupDiagnosticActionResponse;
import com.codetruck.gps.engine.models.MapNextGroupDiagnosticOrServiceResult;
import com.codetruck.gps.engine.repositories.ActionRepository;
import com.codetruck.gps.engine.repositories.GroupDiagnosticRepository;
import com.codetruck.gps.engine.repositories.MapGroupDiagnosticActionResponseRepository;
import com.codetruck.gps.engine.repositories.MapNextGroupDiagnosticOrServiceResultRepository;
import com.codetruck.gps.engine.repositories.ServiceResultRepository;
import com.codetruck.gps.engine.services.MapNextGroupDiagnosticOrServiceResultService;

@Service
public class MapNextGroupDiagnosticOrServiceResultServiceImpl implements MapNextGroupDiagnosticOrServiceResultService {

	final MapNextGroupDiagnosticOrServiceResultRepository diagnosticOrServiceResultRepository;
	final GroupDiagnosticRepository groupDiagnosticRepository;
	final ServiceResultRepository serviceResultRepository;
	final MapGroupDiagnosticActionResponseRepository actionResponseRepository;
	final ActionRepository actionRepository;
	
	public MapNextGroupDiagnosticOrServiceResultServiceImpl(
			MapNextGroupDiagnosticOrServiceResultRepository diagnosticOrServiceResultRepository, 
			GroupDiagnosticRepository groupDiagnosticRepository, 
			ServiceResultRepository serviceResultRepository, 
			MapGroupDiagnosticActionResponseRepository actionResponseRepository, ActionRepository actionRepository
	) {
		this.diagnosticOrServiceResultRepository = diagnosticOrServiceResultRepository;
		this.groupDiagnosticRepository = groupDiagnosticRepository;
		this.serviceResultRepository = serviceResultRepository;
		this.actionResponseRepository = actionResponseRepository;
		this.actionRepository = actionRepository;
	}

	@Transactional
	@Override
	public void save(UUID groupId, FlowServiceDto flowServiceDto) throws Exception {
		
		
		var mapsGdResGd = flowServiceDto.getFlows();
		
		var groupDiagnosticCurrent = this.groupDiagnosticRepository.findById(groupId);
		
		
		if (groupDiagnosticCurrent.isEmpty()) {
			throw new Exception("Group Diagnostic not found.");
		}
		
		for (MapNextGroupDiagnosticOrServiceResultDto dto : mapsGdResGd) {
						
			var flowMapping = new MapNextGroupDiagnosticOrServiceResult();
			flowMapping.setDiagnosticCurrent(groupDiagnosticCurrent.get());
			
			if (dto.getId_GD_PROX() != null) {

				var groupDiagnosticNext = this.groupDiagnosticRepository.findById(dto.getId_GD_PROX());
				
				if (groupDiagnosticNext.isEmpty()) {
					throw new Exception("Group Diagnostic NEXT not found.");
				}
				
				flowMapping.setDiagnosticNext(groupDiagnosticNext.get());
			}
			
			if (dto.getId_RES_ATEND() != null) {
				
				var serviceResultOptional = this.serviceResultRepository.findById(dto.getId_RES_ATEND());
				
				if (serviceResultOptional.isEmpty()) {
					throw new Exception("Service Result not found.");
				}
				
				flowMapping.setServiceResult(serviceResultOptional.get());
				
			}
			
			flowMapping.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
			flowMapping.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));
			flowMapping.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
			flowMapping.setUserLastUpdated(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
			
			this.diagnosticOrServiceResultRepository.save(flowMapping);
			
			var actionResponseMapping = dto.getActionResponseMapping();
			
			for (MapGroupDiagnosticActionResponseDto actionResponseDto : actionResponseMapping) {
				
				var actionModelOptional = actionRepository.findById(actionResponseDto.getActionId());
				
				var actionResponseModel = new MapGroupDiagnosticActionResponse();
				
				actionResponseModel.setDiagnosticOrServiceResultId(flowMapping);
				
				actionResponseModel.setAction(actionModelOptional.get());
				actionResponseModel.setResponse(actionResponseDto.getActionResponse());
				actionResponseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
				actionResponseModel.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
				
				this.actionResponseRepository.save(actionResponseModel);
				
			}
			
		}
		
	}

}
