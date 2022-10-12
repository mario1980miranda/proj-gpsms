package com.codetruck.gps.engine.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.gps.engine.services.GroupDiagnosticService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/group-diagnostic")
public class GroupDiagnosticController {

	final GroupDiagnosticService groupDiagnosticService;

	public GroupDiagnosticController(GroupDiagnosticService groupDiagnosticService) {
		this.groupDiagnosticService = groupDiagnosticService;
	}
	
	
	
}
