package com.codetruck.gps.engine.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codetruck.gps.engine.models.ActionModel;
import com.codetruck.gps.engine.repositories.ActionRepository;
import com.codetruck.gps.engine.services.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	final ActionRepository actionRepository;

	public ActionServiceImpl(ActionRepository actionRepository) {
		this.actionRepository = actionRepository;
	}

	@Transactional
	@Override
	public ActionModel save(ActionModel actionModel) {
		return this.actionRepository.save(actionModel);
	}

	@Override
	public Page<ActionModel> findAll(Pageable pageable) {
		return this.actionRepository.findAll(pageable);
	}

	@Override
	public Optional<ActionModel> findById(UUID actionId) {
		return this.actionRepository.findById(actionId);
	}

}
