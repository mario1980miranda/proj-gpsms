package com.codetruck.gps.engine.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.codetruck.gps.engine.enums.ActionResponseEnum;
import com.codetruck.gps.engine.models.pks.FlowMappingActionResponsePk;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@IdClass(FlowMappingActionResponsePk.class)
@Table(name = "TB_FLOW_MAP_GD_RESULT_ACT_RESP")
public class FlowMappingActionResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_RL_GD_SR_GD")
	private FlowMappingGroupDiagnosticModel flowMappingGroupDiagnosticId;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_ACTION")
	private ActionModel actionId;
	
	@Id
	@Column(name = "ACTION_RESPONSE")
	@Enumerated(EnumType.ORDINAL)
	private ActionResponseEnum actionResponse;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
	private UUID userCreation;
	
}
