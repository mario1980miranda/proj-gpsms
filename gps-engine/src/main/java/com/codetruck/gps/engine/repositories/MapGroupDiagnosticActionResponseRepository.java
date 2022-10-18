package com.codetruck.gps.engine.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetruck.gps.engine.models.MapGroupDiagnosticActionResponse;

public interface MapGroupDiagnosticActionResponseRepository extends JpaRepository<MapGroupDiagnosticActionResponse, UUID> {

}
