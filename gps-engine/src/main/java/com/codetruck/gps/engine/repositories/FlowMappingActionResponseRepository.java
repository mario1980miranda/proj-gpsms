package com.codetruck.gps.engine.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.codetruck.gps.engine.models.FlowMappingActionResponseModel;

public interface FlowMappingActionResponseRepository extends JpaRepository<FlowMappingActionResponseModel, UUID> {

	@Query(value = "SELECT * FROM tb_flow_map_gd_result_act_resp where id_rl_gd_sr_gd = :idMapeamento", nativeQuery = true)
	Optional<FlowMappingActionResponseModel> findFlowByMappingGroupDiagnosticId(@Param("idMapeamento") UUID idMapeamento);
}
