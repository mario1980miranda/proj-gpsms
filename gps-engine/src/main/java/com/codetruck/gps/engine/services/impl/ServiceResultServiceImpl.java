package com.codetruck.gps.engine.services.impl;

import org.springframework.stereotype.Service;

import com.codetruck.gps.engine.repositories.ServiceResultRepository;
import com.codetruck.gps.engine.services.ServiceResultService;

@Service
public class ServiceResultServiceImpl implements ServiceResultService {

	final ServiceResultRepository serviceResultRepository;

	public ServiceResultServiceImpl(ServiceResultRepository serviceResultRepository) {
		super();
		this.serviceResultRepository = serviceResultRepository;
	}
	
	
	
}
