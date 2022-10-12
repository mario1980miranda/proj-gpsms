package com.codetruck.gps.engine.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codetruck.gps.engine.models.ServiceResultModel;

public interface ServiceResultService {

	ServiceResultModel save(ServiceResultModel serviceResultModel);

	Page<ServiceResultModel> findAll(Pageable pageable);

}
