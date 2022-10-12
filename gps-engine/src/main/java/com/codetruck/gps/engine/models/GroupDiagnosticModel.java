package com.codetruck.gps.engine.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_GROUPS_DIAGNOSTICS")
public class GroupDiagnosticModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID groupDiagnosticId;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
	private UUID userCreation;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime lastUpdatedDate;
	
	@Column(nullable = false)
	private UUID userLastUpdated;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private UUID typeId;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "TB_RL_GDS_ACTS",
            joinColumns = @JoinColumn(name = "group_diagnostic_id"),
            inverseJoinColumns = @JoinColumn(name = "action_id")
    )
	private Set<ActionModel> actions = new HashSet<>();
	
}
