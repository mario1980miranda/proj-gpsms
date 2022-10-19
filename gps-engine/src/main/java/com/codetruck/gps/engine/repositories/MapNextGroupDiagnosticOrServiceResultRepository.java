package com.codetruck.gps.engine.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetruck.gps.engine.models.GroupDiagnosticModel;
import com.codetruck.gps.engine.models.MapNextGroupDiagnosticOrServiceResult;

public interface MapNextGroupDiagnosticOrServiceResultRepository extends JpaRepository<MapNextGroupDiagnosticOrServiceResult, UUID> {

	List<MapNextGroupDiagnosticOrServiceResult> findByDiagnosticCurrent(GroupDiagnosticModel diagnosticCurrent);
}
