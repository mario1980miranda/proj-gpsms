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

import com.codetruck.gps.engine.enums.ActionResponse;
import com.codetruck.gps.engine.models.pks.MapGroupDiagnosticActionResponsePk;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@IdClass(MapGroupDiagnosticActionResponsePk.class)
@Table(name = "TB_RL_GD_SR_GD_ACT")
public class MapGroupDiagnosticActionResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_RL_GD_SR_GD")
	private MapNextGroupDiagnosticOrServiceResult diagnosticOrServiceResultId;
	
	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ACTION")
	private Action action;
	
	@Id
	@Column(name = "ACTION_RESPONSE")
	@Enumerated(EnumType.ORDINAL)
	private ActionResponse response;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
	private UUID userCreation;
	
}
