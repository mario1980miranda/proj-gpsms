package com.codetruck.gps.engine.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codetruck.gps.engine.models.ServiceResultModel;
import com.codetruck.gps.engine.repositories.ServiceResultRepository;
import com.codetruck.gps.engine.services.ServiceResultService;

@Service
public class ServiceResultServiceImpl implements ServiceResultService {

	final ServiceResultRepository serviceResultRepository;

	public ServiceResultServiceImpl(ServiceResultRepository serviceResultRepository) {
		this.serviceResultRepository = serviceResultRepository;
	}

	@Override
	public ServiceResultModel save(ServiceResultModel serviceResultModel) {
		return this.serviceResultRepository.save(serviceResultModel);
	}

	@Override
	public Page<ServiceResultModel> findAll(Pageable pageable) {
		return this.serviceResultRepository.findAll(pageable);
	}
	
}
