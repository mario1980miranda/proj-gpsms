package com.codetruck.gps.engine.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.gps.engine.services.ServiceResultService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/service-result")
public class ServiceResultController {

	final ServiceResultService serviceResultService;

	public ServiceResultController(ServiceResultService serviceResultService) {
		this.serviceResultService = serviceResultService;
	}
	
	
	
}
