package com.codetruck.authuser.services;

import java.util.List;

import com.codetruck.authuser.models.RoleModel;

public interface RoleService {

	long count();

	void save(RoleModel roleModel);

	List<RoleModel> findAll();
}
