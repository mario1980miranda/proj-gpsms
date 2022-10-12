package com.codetruck.gps.engine.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.repositories.GroupDiagnosticRepository;
import com.codetruck.gps.engine.services.GroupDiagnosticService;

@Service
public class GroupDiagnosticServiceImpl implements GroupDiagnosticService {

	final GroupDiagnosticRepository diagnosticRepository;

	public GroupDiagnosticServiceImpl(GroupDiagnosticRepository diagnosticRepository) {
		this.diagnosticRepository = diagnosticRepository;
	}

	@Transactional
	@Override
	public GroupDiagnosticModel save(GroupDiagnosticModel groupDiagnosticModel) {
		return this.diagnosticRepository.save(groupDiagnosticModel);
	}
	
}
