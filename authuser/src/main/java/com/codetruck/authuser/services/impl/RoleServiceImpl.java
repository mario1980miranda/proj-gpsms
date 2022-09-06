package com.codetruck.authuser.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codetruck.authuser.models.RoleModel;
import com.codetruck.authuser.repositories.RoleRepository;
import com.codetruck.authuser.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	final RoleRepository roleRepository;
	
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public long count() {
		return this.roleRepository.count();
	}

	@Override
	public void save(RoleModel roleModel) {
		this.roleRepository.save(roleModel);
	}

	@Override
	public List<RoleModel> findAll() {
		return this.roleRepository.findAll();
	}

}
