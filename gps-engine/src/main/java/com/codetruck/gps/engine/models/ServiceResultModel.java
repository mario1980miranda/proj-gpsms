package com.codetruck.gps.engine.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_SERVICE_RESULT")
public class ServiceResultModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID serviceResultId;
	
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
	
	@Column(nullable = false, length = 150)
	private String description;
	
	@Column(nullable = false, unique = true, length = 150)
	private String code;
	
	@Column(nullable = false)
	private Boolean active;

}
