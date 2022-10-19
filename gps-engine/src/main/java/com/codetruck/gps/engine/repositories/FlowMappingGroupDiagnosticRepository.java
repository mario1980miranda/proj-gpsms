package com.codetruck.gps.engine.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.models.FlowMappingGroupDiagnosticModel;

public interface FlowMappingGroupDiagnosticRepository extends JpaRepository<FlowMappingGroupDiagnosticModel, UUID> {

	List<FlowMappingGroupDiagnosticModel> findByGroupDiagnosticModelCurrent(GroupDiagnosticModel groupDiagnosticModelCurrent);
}
