package com.codetruck.gps.engine.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codetruck.gps.engine.models.ServiceResultModel;

public interface ServiceResultService {

	ServiceResultModel save(ServiceResultModel serviceResultModel);

	Page<ServiceResultModel> findAll(Pageable pageable);

	Optional<ServiceResultModel> findById(UUID serviceResultId);

}
