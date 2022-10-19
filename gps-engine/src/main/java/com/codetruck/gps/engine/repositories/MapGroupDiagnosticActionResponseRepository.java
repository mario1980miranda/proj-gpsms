package com.codetruck.gps.engine.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codetruck.gps.engine.models.MapGroupDiagnosticActionResponse;

public interface MapGroupDiagnosticActionResponseRepository extends JpaRepository<MapGroupDiagnosticActionResponse, UUID> {

	@Query(value = "SELECT * FROM tb_rl_gd_sr_gd_act where id_rl_gd_sr_gd = :idMapeamento", nativeQuery = true)
	Optional<MapGroupDiagnosticActionResponse> buscarPeloMapeamento(@Param("idMapeamento") UUID idMapeamento);
}
