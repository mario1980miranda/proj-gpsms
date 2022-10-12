package com.codetruck.gps.engine.services.impl;

import org.springframework.stereotype.Service;

import com.codetruck.gps.engine.repositories.GroupDiagnosticRepository;
import com.codetruck.gps.engine.services.GroupDiagnosticService;

@Service
public class GroupDiagnosticServiceImpl implements GroupDiagnosticService {

	final GroupDiagnosticRepository diagnosticRepository;

	public GroupDiagnosticServiceImpl(GroupDiagnosticRepository diagnosticRepository) {
		this.diagnosticRepository = diagnosticRepository;
	}
	
	
	
}
