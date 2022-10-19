package com.codetruck.gps.engine.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codetruck.gps.engine.models.ServiceResultModel;
import com.codetruck.gps.engine.repositories.ServiceResultRepository;
import com.codetruck.gps.engine.services.ServiceResultService;

@Service
public class ServiceResultServiceImpl implements ServiceResultService {

	final ServiceResultRepository serviceResultRepository;

	public ServiceResultServiceImpl(ServiceResultRepository serviceResultRepository) {
		this.serviceResultRepository = serviceResultRepository;
	}

	@Transactional
	@Override
	public ServiceResultModel save(ServiceResultModel serviceResultModel) {
		return this.serviceResultRepository.save(serviceResultModel);
	}

	@Override
	public Page<ServiceResultModel> findAll(Pageable pageable) {
		return this.serviceResultRepository.findAll(pageable);
	}

	@Override
	public Optional<ServiceResultModel> findById(UUID serviceResultId) {
		return this.serviceResultRepository.findById(serviceResultId);
	}
	
}
