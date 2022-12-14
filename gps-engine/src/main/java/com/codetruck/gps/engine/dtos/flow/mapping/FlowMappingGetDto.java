package com.codetruck.gps.engine.dtos.flow.mapping;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class FlowMappingGetDto {

	private Set<FlowMappingGroupDiagnosticGetDto> flows = new HashSet<>();
}
