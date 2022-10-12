package com.codetruck.gps.engine.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.codetruck.gps.engine.models.ActionModel;
import com.codetruck.gps.engine.repositories.ActionRepository;
import com.codetruck.gps.engine.services.ActionService;

@Service
public class ActionServiceImpl implements ActionService {

	final ActionRepository actionRepository;

	public ActionServiceImpl(ActionRepository actionRepository) {
		this.actionRepository = actionRepository;
	}

	@Override
	public ActionModel save(ActionModel actionModel) {
		return this.actionRepository.save(actionModel);
	}

	@Override
	public Page<ActionModel> findAll(Pageable pageable) {
		return this.actionRepository.findAll(pageable);
	}

}
