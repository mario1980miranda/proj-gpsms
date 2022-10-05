package com.codetruck.authuser.dtos;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RoleDto {

	public interface RoleView {
		public interface RegistrationPost {}
	}
	
	@NotBlank(groups = RoleView.RegistrationPost.class)
	private UUID roleId;
	
	@NotBlank(groups = RoleView.RegistrationPost.class)
	private String authority;
	
}
