package com.codetruck.gps.engine.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.codetruck.gps.engine.models.ActionModel;

public interface ActionService {

	ActionModel save(ActionModel actionModel);

	Page<ActionModel> findAll(Pageable pageable);

	Optional<ActionModel> findById(UUID actionId);

}
