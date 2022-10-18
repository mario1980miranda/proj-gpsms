package com.codetruck.gps.engine.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.gps.engine.dtos.FlowServiceDto;
import com.codetruck.gps.engine.dtos.GroupDiagnosticDto;
import com.codetruck.gps.engine.dtos.MapGroupDiagnosticActionResponseDto;
import com.codetruck.gps.engine.dtos.MapNextGroupDiagnosticOrServiceResultDto;
import com.codetruck.gps.engine.enums.ActionResponse;
import com.codetruck.gps.engine.models.ActionModel;
import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.services.ActionService;
import com.codetruck.gps.engine.services.GroupDiagnosticService;
import com.codetruck.gps.engine.services.MapNextGroupDiagnosticOrServiceResultService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping
public class GroupDiagnosticController {

	final GroupDiagnosticService groupDiagnosticService;
	final ActionService actionService;
	final MapNextGroupDiagnosticOrServiceResultService diagnosticOrServiceResultService;

	public GroupDiagnosticController(
			GroupDiagnosticService groupDiagnosticService, 
			ActionService actionService, 
			MapNextGroupDiagnosticOrServiceResultService diagnosticOrServiceResultService
	) {
		
		this.groupDiagnosticService = groupDiagnosticService;
		this.actionService = actionService;
		this.diagnosticOrServiceResultService = diagnosticOrServiceResultService;
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
			UUID groupId,
			@RequestBody
			FlowServiceDto flowServiceDto
	) {
		
		var mapsGdResGd = flowServiceDto.getFlows();
		
		for (MapNextGroupDiagnosticOrServiceResultDto flow : mapsGdResGd) {
			flow.setId_GD_ATUAL(groupId);
		}
				
		return ResponseEntity.status(HttpStatus.OK).body(flowServiceDto);
	}
	
	@PostMapping("/group-diagnostic/{groupId}/flow-mapping")
	public ResponseEntity<Object> saveFlowMapping(
			@PathVariable(value = "groupId")
			UUID groupId,
			@RequestBody
			FlowServiceDto flowServiceDto
	) {
		
		try {
			this.diagnosticOrServiceResultService.save(groupId, flowServiceDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		return ResponseEntity.status(HttpStatus.OK).body(flowServiceDto);
	}
	
}
