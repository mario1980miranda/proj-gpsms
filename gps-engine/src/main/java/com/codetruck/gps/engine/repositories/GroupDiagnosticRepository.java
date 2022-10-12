package com.codetruck.gps.engine.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetruck.gps.engine.models.GroupDiagnosticModel;

public interface GroupDiagnosticRepository extends JpaRepository<GroupDiagnosticModel, UUID> {

}
