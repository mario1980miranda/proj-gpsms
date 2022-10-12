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

import com.codetruck.gps.engine.dtos.ServiceResultDto;
import com.codetruck.gps.engine.models.ServiceResultModel;
import com.codetruck.gps.engine.services.ServiceResultService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/services-results")
public class ServiceResultController {

	final ServiceResultService serviceResultService;

	public ServiceResultController(ServiceResultService serviceResultService) {
		this.serviceResultService = serviceResultService;
	}
	
	@PostMapping
	public ResponseEntity<Object> saveServiceResult(
			@RequestBody
			ServiceResultDto serviceResultDto
	) {
		
		log.info("POST saveServiceResult");
		
		var serviceResultModel = new ServiceResultModel();
		serviceResultModel.setCode(serviceResultDto.getCode());
		serviceResultModel.setDescription(serviceResultDto.getDescription());
		
		serviceResultModel.setActive(Boolean.TRUE);
		serviceResultModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		serviceResultModel.setLastUpdatedDate(LocalDateTime.now(ZoneId.of("UTC")));
		serviceResultModel.setUserCreation(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
		serviceResultModel.setUserLastUpdated(UUID.fromString("49ac1f76-8129-4340-8b04-71bdf3b6cb15"));
		
		serviceResultModel = this.serviceResultService.save(serviceResultModel);
		
		log.debug("ServiceResultModel saved with Id: {}", serviceResultModel.getServiceResultId());
				
		return ResponseEntity.status(HttpStatus.CREATED).body(serviceResultModel);
	}
	
	@GetMapping
	public ResponseEntity<Page<ServiceResultModel>> getAllServicesResults(
			@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC)
			Pageable pageable
	) {
		
		log.debug("GET getAllServicesResults");
		
		Page<ServiceResultModel> serviceResultPage = this.serviceResultService.findAll(pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(serviceResultPage);
	}
	
}
