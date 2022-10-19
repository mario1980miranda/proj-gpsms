package com.codetruck.gps.engine.services;

import java.util.List;
import java.util.UUID;

import com.codetruck.gps.engine.dtos.FlowServiceDto;
import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.models.MapNextGroupDiagnosticOrServiceResult;

public interface MapNextGroupDiagnosticOrServiceResultService {

	void save(UUID groupId, FlowServiceDto flowServiceDto) throws Exception;

	List<MapNextGroupDiagnosticOrServiceResult> findByDiagnosticCurrent(GroupDiagnosticModel groupDiagnosticCurrent);

}
