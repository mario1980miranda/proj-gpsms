package com.codetruck.typification.dtos;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypeDto {

	@NotBlank
	private String description;
	
	private String ask;
	
	@NotBlank
	private Boolean active;
	
}
