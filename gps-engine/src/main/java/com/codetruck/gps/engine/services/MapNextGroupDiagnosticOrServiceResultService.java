package com.codetruck.gps.engine.services;

import java.util.UUID;

import com.codetruck.gps.engine.dtos.FlowServiceDto;

public interface MapNextGroupDiagnosticOrServiceResultService {

	void save(UUID groupId, FlowServiceDto flowServiceDto) throws Exception;

}
