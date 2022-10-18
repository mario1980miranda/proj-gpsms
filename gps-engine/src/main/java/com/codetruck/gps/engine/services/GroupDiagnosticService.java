package com.codetruck.gps.engine.services;

import java.util.Optional;
import java.util.UUID;

import com.codetruck.gps.engine.models.GroupDiagnosticModel;

public interface GroupDiagnosticService {

	GroupDiagnosticModel save(GroupDiagnosticModel groupDiagnosticModel);

	Optional<GroupDiagnosticModel> findByTypeId(UUID typeId);

	Optional<GroupDiagnosticModel> findById(UUID groupId);

}
