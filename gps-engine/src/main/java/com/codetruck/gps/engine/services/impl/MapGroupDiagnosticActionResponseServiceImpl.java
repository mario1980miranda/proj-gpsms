package com.codetruck.gps.engine.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.codetruck.gps.engine.models.MapGroupDiagnosticActionResponse;
import com.codetruck.gps.engine.repositories.MapGroupDiagnosticActionResponseRepository;
import com.codetruck.gps.engine.services.MapGroupDiagnosticActionResponseService;

@Service
public class MapGroupDiagnosticActionResponseServiceImpl implements MapGroupDiagnosticActionResponseService {

	private MapGroupDiagnosticActionResponseRepository mapGroupDiagnosticActionResponseRepository;
	
	public MapGroupDiagnosticActionResponseServiceImpl(
			MapGroupDiagnosticActionResponseRepository mapGroupDiagnosticActionResponseRepository
	) {
		
		this.mapGroupDiagnosticActionResponseRepository = mapGroupDiagnosticActionResponseRepository;
	}



	@Override
	public Optional<MapGroupDiagnosticActionResponse> findById(UUID mapId) {
		return this.mapGroupDiagnosticActionResponseRepository.buscarPeloMapeamento(mapId);
	}

}
