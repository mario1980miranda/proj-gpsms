package com.codetruck.gps.engine.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.gps.engine.dtos.ActionDto;
import com.codetruck.gps.engine.models.ActionModel;
import com.codetruck.gps.engine.services.ActionService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/actions")
public class ActionController {

	final ActionService actionService;

	public ActionController(ActionService actionService) {
		this.actionService = actionService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveAction(
			@RequestBody
			ActionDto actionDto
	) {
		
		log.info("POST saveAction");
		
		var actionModel = new ActionModel();
		actionModel.setName(actionDto.getName());
		actionModel.setObservation(actionDto.getObservation());
		
		actionModel.setAtive(Boolean.TRUE);
		actionModel.setAutomatic(Boolean.FALSE);
		actionModel.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
		actionModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		actionModel.setUserLastUpdated(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
		actionModel.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));
		actionModel.setManualInCaseOfFail(Boolean.FALSE);
		
		actionModel = this.actionService.save(actionModel);
		
		log.info("Action saved with id {}", actionModel.getActionId());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(actionModel);
	}
	
	@GetMapping
	public ResponseEntity<Page<ActionModel>> getAllActions(
			@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC)
			Pageable pageable
	) {
		
		Page<ActionModel> actionModelPage = this.actionService.findAll(pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(actionModelPage);
	}
	
}
