package com.codetruck.typification.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "TB_TYPE")
public class TypeModel extends RepresentationModel<TypeModel> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID typeId;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime creationDate;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
	private LocalDateTime lastUpdateDate;
	
	@Column(nullable = false)
	private UUID userIdCreated;
	
	@Column(nullable = false)
	private UUID userIdLastUpdated;
	
	@Column(nullable = false, length = 50)
	private String description;
	
	@Column(length = 150)
	private String ask;
	
	@Column(nullable = false)
	private Boolean active = Boolean.FALSE;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_TYPE_ID")
	private TypeModel parentType;
	
}
