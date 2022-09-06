package com.codetruck.authuser.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.authuser.models.RoleModel;
import com.codetruck.authuser.services.RoleService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/roles")
public class RoleController {

	final RoleService roleService;
	
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public ResponseEntity<List<RoleModel>> getAllRoles() {
		
		log.debug("GET RoleController getAllRoles");
		
		List<RoleModel> roles = this.roleService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(roles);
	}
}
