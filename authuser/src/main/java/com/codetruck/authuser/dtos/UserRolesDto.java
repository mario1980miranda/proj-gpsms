package com.codetruck.authuser.dtos;

import java.util.HashSet;
import java.util.Set;

import com.codetruck.authuser.models.RoleModel;

import lombok.Data;

@Data
public class UserRolesDto {

	private Set<RoleModel> roles = new HashSet<>();
}
