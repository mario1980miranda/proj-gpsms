package com.codetruck.gps.engine.dtos;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class FlowServiceSearchDto {

	private Set<MapNextGroupDiagnosticOrServiceResultSearchDto> flows = new HashSet<>();
}
