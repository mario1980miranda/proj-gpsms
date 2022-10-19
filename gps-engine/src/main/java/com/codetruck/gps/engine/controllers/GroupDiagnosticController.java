package com.codetruck.gps.engine.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.gps.engine.dtos.GroupDiagnosticDto;
import com.codetruck.gps.engine.dtos.flow.mapping.FlowMappingDto;
import com.codetruck.gps.engine.dtos.flow.mapping.FlowMappingGetDto;
import com.codetruck.gps.engine.dtos.flow.mapping.FlowMappingGroupDiagnosticDto;
import com.codetruck.gps.engine.dtos.flow.mapping.FlowMappingGroupDiagnosticGetDto;
import com.codetruck.gps.engine.models.ActionModel;
import com.codetruck.gps.engine.models.FlowMappingActionResponseModel;
import com.codetruck.gps.engine.models.FlowMappingGroupDiagnosticModel;
import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.services.ActionService;
import com.codetruck.gps.engine.services.FlowMappingActionResponseService;
import com.codetruck.gps.engine.services.FlowMappingGroupDiagnosticService;
import com.codetruck.gps.engine.services.GroupDiagnosticService;
import com.codetruck.gps.engine.services.ServiceResultService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping
public class GroupDiagnosticController {

	final GroupDiagnosticService groupDiagnosticService;
	final ActionService actionService;
	final FlowMappingGroupDiagnosticService flowMappingGroupDiagnosticService;
	final FlowMappingActionResponseService flowMappingActionResponseService;
	final ServiceResultService serviceResultService;

	public GroupDiagnosticController(
			GroupDiagnosticService groupDiagnosticService, 
			ActionService actionService, 
			FlowMappingGroupDiagnosticService diagnosticOrServiceResultService, 
			FlowMappingActionResponseService actionResponseService, ServiceResultService serviceResultService
	) {
		
		this.groupDiagnosticService = groupDiagnosticService;
		this.actionService = actionService;
		this.flowMappingGroupDiagnosticService = diagnosticOrServiceResultService;
		this.flowMappingActionResponseService = actionResponseService;
		this.serviceResultService = serviceResultService;
	}
		
	@GetMapping("/type/{typeId}/group-diagnostic")
	public ResponseEntity<Object> getGroupDiagnosticInitial(
			@PathVariable(value = "typeId")
			UUID typeId
	) {
		
		Optional<GroupDiagnosticModel> groupDiagnosticOptional =  this.groupDiagnosticService.findByTypeId(typeId);
		
		if (groupDiagnosticOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Initial Group Diagnostic not found for typeId: " + typeId);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(groupDiagnosticOptional.get());
	}
		
	@PostMapping("/type/{typeId}/group-diagnostic")
	public ResponseEntity<Object> saveGroupDiagnosticIntoType(
			@PathVariable(value = "typeId")
			UUID typeId,
			@RequestBody
			GroupDiagnosticDto groupDiagnosticDto
	) {
		
		log.debug("POST saveGroupDiagnostic for typification: {}", typeId);
		
		// verifier si le type existe
		// verifier si le type est enfant et non parent
		
		var groupDiagnosticModel = new GroupDiagnosticModel();
		groupDiagnosticModel.setName(groupDiagnosticDto.getName());
		
		groupDiagnosticModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		groupDiagnosticModel.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));
		groupDiagnosticModel.setTypeId(typeId);
		groupDiagnosticModel.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
		groupDiagnosticModel.setUserLastUpdated(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
		
		groupDiagnosticModel = this.groupDiagnosticService.save(groupDiagnosticModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(groupDiagnosticModel);
	}
	
	@PostMapping("/group-diagnostic/{groupId}/action/{actionId}/add")
	public ResponseEntity<Object> addActionIntoGroupDiagnostic(
			@PathVariable(value = "groupId")
			UUID groupId,
			@PathVariable(value = "actionId")
			UUID actionId
	) {
		
		Optional<GroupDiagnosticModel> groupDiagnosticOptional =  this.groupDiagnosticService.findById(groupId);
		
		if (groupDiagnosticOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group Diagnostic not found for id: " + groupId);
		}
		
		Optional<ActionModel> actionModelOptional = this.actionService.findById(actionId);
		
		if (actionModelOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Action not found for id: " + actionId);
		}
		
		var groupDiagnosticModel = groupDiagnosticOptional.get();
		var actionModel = actionModelOptional.get();
		
		groupDiagnosticModel.getActions().add(actionModel);
		
		this.groupDiagnosticService.save(groupDiagnosticModel);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Action added.");
	}
	
	@PostMapping("/group-diagnostic/{groupId}/action/{actionId}/remove")
	public ResponseEntity<Object> removeActionFromGroupDiagnostic(
			@PathVariable(value = "groupId")
			UUID groupId,
			@PathVariable(value = "actionId")
			UUID actionId
	) {
		
		Optional<GroupDiagnosticModel> groupDiagnosticOptional =  this.groupDiagnosticService.findById(groupId);
		
		if (groupDiagnosticOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group Diagnostic not found for id: " + groupId);
		}
		
				
		var groupDiagnosticModel = groupDiagnosticOptional.get();
		
		var actionToRemove = groupDiagnosticModel.getActions().stream().filter(a -> a.getActionId().equals(actionId)).findFirst();
		
		if (actionToRemove.isPresent()) {
			groupDiagnosticModel.getActions().remove(actionToRemove.get());
		}
				
		this.groupDiagnosticService.save(groupDiagnosticModel);
		
		return ResponseEntity.status(HttpStatus.OK).body("Action Removed.");
	}
	
	@GetMapping("/group-diagnostic/{groupId}")
	public ResponseEntity<Object> getGroupDiagnostic(
			@PathVariable(value = "groupId")
			UUID groupId
	) {
		
		Optional<GroupDiagnosticModel> groupDiagnosticOptional =  this.groupDiagnosticService.findById(groupId);
		
		if (groupDiagnosticOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group Diagnostic not found for id: " + groupId);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(groupDiagnosticOptional.get());
	}
	
	@GetMapping("/group-diagnostic/{groupId}/flow-mapping")
	public ResponseEntity<Object> getFlowMapping(
			@PathVariable(value = "groupId")
			UUID groupId
	) {
		
		var groupDiagnosticModelOptional = this.groupDiagnosticService.findById(groupId);
		
		if (groupDiagnosticModelOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group Diagnostic not found for id: " + groupId);
		}
		
		List<FlowMappingGroupDiagnosticModel> flowMappingGroupDiagnosticModelList = this.flowMappingGroupDiagnosticService
				.findByDiagnosticCurrent(groupDiagnosticModelOptional.get());
		var flowMappingGetDto = new FlowMappingGetDto();
		
		for (FlowMappingGroupDiagnosticModel flowMappingGroupDiagnosticModel : flowMappingGroupDiagnosticModelList) {
			var gdRsGdResponseDto = new FlowMappingGroupDiagnosticGetDto();
			gdRsGdResponseDto
					.setFlowMappingGroupDiagnosticId(flowMappingGroupDiagnosticModel.getFlowMappingGroupDiagnosticId());
			gdRsGdResponseDto.setGroupDiagnosticCurrentId(
					flowMappingGroupDiagnosticModel.getGroupDiagnosticModelCurrent().getGroupDiagnosticId());
			gdRsGdResponseDto
					.setGroupDiagnosticNextId(flowMappingGroupDiagnosticModel.getGroupDiagnosticModelNext() != null
							? flowMappingGroupDiagnosticModel.getGroupDiagnosticModelNext().getGroupDiagnosticId()
							: null);
			gdRsGdResponseDto.setServiceResultId(flowMappingGroupDiagnosticModel.getServiceResultModel() != null
					? flowMappingGroupDiagnosticModel.getServiceResultModel().getServiceResultId()
					: null);

			var flowMappingActionResponseModelOptional = this.flowMappingActionResponseService
					.findFlowByMappingGroupDiagnosticId(flowMappingGroupDiagnosticModel.getFlowMappingGroupDiagnosticId());

			gdRsGdResponseDto.setActionId(flowMappingActionResponseModelOptional.get().getActionId().getActionId());
			gdRsGdResponseDto.setActionResponse(flowMappingActionResponseModelOptional.get().getActionResponse());

			flowMappingGetDto.getFlows().add(gdRsGdResponseDto);
		}
				
		return ResponseEntity.status(HttpStatus.OK).body(flowMappingGetDto);
	}
	
	@PostMapping("/group-diagnostic/{groupId}/flow-mapping")
	public ResponseEntity<Object> saveFlowMappingGroupDiagnostic(
			@PathVariable(value = "groupId")
			UUID groupId,
			@RequestBody
			FlowMappingDto flowMappingDto
	) {
		
		var flowMapGroupDiagnosticDtos = flowMappingDto.getFlowMappingGroupDiagnosticDtos();

		var groupDiagnosticModelCurrent = this.groupDiagnosticService.findById(groupId);

		if (groupDiagnosticModelCurrent.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group Diagnostic CURRENT not found for id: " + groupId);
		}
		
		FlowMappingGetDto flowMappingGetResponseDto = new FlowMappingGetDto();

		for (FlowMappingGroupDiagnosticDto flowMappingGroupDiagnosticDto : flowMapGroupDiagnosticDtos) {

			var flowMappingGroupDiagnosticModel = new FlowMappingGroupDiagnosticModel();
			
			flowMappingGroupDiagnosticModel.setGroupDiagnosticModelCurrent(groupDiagnosticModelCurrent.get());

			if (flowMappingGroupDiagnosticDto.getGroupdDiagnosticNextId() != null) {

				var groupDiagnosticNext = this.groupDiagnosticService.findById(flowMappingGroupDiagnosticDto.getGroupdDiagnosticNextId());

				if (groupDiagnosticNext.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Group Diagnostic NEXT not found for id: " + flowMappingGroupDiagnosticDto.getGroupdDiagnosticNextId());
				}

				flowMappingGroupDiagnosticModel.setGroupDiagnosticModelNext(groupDiagnosticNext.get());
			}

			if (flowMappingGroupDiagnosticDto.getServiceResultId() != null) {

				var serviceResultOptional = this.serviceResultService.findById(flowMappingGroupDiagnosticDto.getServiceResultId());

				if (serviceResultOptional.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Service Result not found for id: " + flowMappingGroupDiagnosticDto.getServiceResultId());
				}

				flowMappingGroupDiagnosticModel.setServiceResultModel(serviceResultOptional.get());
			}

			flowMappingGroupDiagnosticModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
			flowMappingGroupDiagnosticModel.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));
			flowMappingGroupDiagnosticModel.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
			flowMappingGroupDiagnosticModel.setUserLastUpdated(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));

			var flowMappingActionResponseDto = flowMappingGroupDiagnosticDto.getFlowMappingActionResponseDto();
			
			var actionModelOptional = this.actionService.findById(flowMappingActionResponseDto.getActionId());
			
			if (actionModelOptional.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Action not found for id: " + flowMappingActionResponseDto.getActionId());
			}

			var actionResponseModel = new FlowMappingActionResponseModel();

			actionResponseModel.setFlowMappingGroupDiagnosticId(flowMappingGroupDiagnosticModel);

			actionResponseModel.setActionId(actionModelOptional.get());
			actionResponseModel.setActionResponse(flowMappingActionResponseDto.getActionResponse());
			actionResponseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
			actionResponseModel.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));

			this.flowMappingGroupDiagnosticService.saveMapGroupDiagnosticAndMapActionResponse(flowMappingGroupDiagnosticModel, actionResponseModel);
			
			var flowMappingGroupDiagnosticGetResponseDto = new FlowMappingGroupDiagnosticGetDto();
			flowMappingGroupDiagnosticGetResponseDto
					.setFlowMappingGroupDiagnosticId(flowMappingGroupDiagnosticModel.getFlowMappingGroupDiagnosticId());
			flowMappingGroupDiagnosticGetResponseDto.setGroupDiagnosticCurrentId(
					flowMappingGroupDiagnosticModel.getGroupDiagnosticModelCurrent().getGroupDiagnosticId());
			flowMappingGroupDiagnosticGetResponseDto
					.setGroupDiagnosticNextId(flowMappingGroupDiagnosticModel.getGroupDiagnosticModelNext() != null
							? flowMappingGroupDiagnosticModel.getGroupDiagnosticModelNext().getGroupDiagnosticId()
							: null);
			flowMappingGroupDiagnosticGetResponseDto
					.setServiceResultId(flowMappingGroupDiagnosticModel.getServiceResultModel() != null
							? flowMappingGroupDiagnosticModel.getServiceResultModel().getServiceResultId()
							: null);

			flowMappingGroupDiagnosticGetResponseDto.setActionId(actionResponseModel.getActionId().getActionId());
			flowMappingGroupDiagnosticGetResponseDto.setActionResponse(actionResponseModel.getActionResponse());

			flowMappingGetResponseDto.getFlows().add(flowMappingGroupDiagnosticGetResponseDto);
		}
						
		return ResponseEntity.status(HttpStatus.CREATED).body(flowMappingGetResponseDto);
	}
	
	@GetMapping("/group-diagnostic/flow-mapping/{mapId}")
	public ResponseEntity<Object> getFlowMappingByMapId(@PathVariable(value = "mapId")UUID mapId) {
		
		var findById = this.flowMappingActionResponseService.findFlowByMappingGroupDiagnosticId(mapId);
		
		return ResponseEntity.status(HttpStatus.OK).body(findById);
	}
	
}
