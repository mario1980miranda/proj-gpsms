package com.codetruck.gps.engine.services;

import java.util.List;

import com.codetruck.gps.engine.models.FlowMappingActionResponseModel;
import com.codetruck.gps.engine.models.FlowMappingGroupDiagnosticModel;
import com.codetruck.gps.engine.models.GroupDiagnosticModel;

public interface FlowMappingGroupDiagnosticService {

	List<FlowMappingGroupDiagnosticModel> findByDiagnosticCurrent(GroupDiagnosticModel groupDiagnosticCurrent);

	void saveMapGroupDiagnosticAndMapActionResponse(
			FlowMappingGroupDiagnosticModel flowMappingGroupDiagnosticModel,
			FlowMappingActionResponseModel flowMappingActionResponseModel);

}
