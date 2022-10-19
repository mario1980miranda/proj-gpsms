package com.codetruck.gps.engine.services;

import java.util.Optional;
import java.util.UUID;

import com.codetruck.gps.engine.models.MapGroupDiagnosticActionResponse;

public interface MapGroupDiagnosticActionResponseService {

	Optional<MapGroupDiagnosticActionResponse> findById(UUID mapId);

}
