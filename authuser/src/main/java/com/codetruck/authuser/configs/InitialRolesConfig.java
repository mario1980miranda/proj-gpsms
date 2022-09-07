package com.codetruck.authuser.configs;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.codetruck.authuser.models.RoleModel;
import com.codetruck.authuser.services.RoleService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class InitialRolesConfig implements ApplicationRunner {

	final RoleService roleService;
	
	public InitialRolesConfig(RoleService roleService) {
		this.roleService = roleService;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if (this.roleService.count() != 0) {
			return;
		}
		
		log.info("There's no ROLES in the system: Registering defaults...");
		
		var adminRoleModel = new RoleModel();
		adminRoleModel.setAuthority("ADMIN");
		this.roleService.save(adminRoleModel);
		log.debug("Admin role successfully saved {}", adminRoleModel.toString());
		
		var wizardRoleModel = new RoleModel();
		wizardRoleModel.setAuthority("WIZARD");
		this.roleService.save(wizardRoleModel);
		log.debug("Wizard role successfully saved {}", wizardRoleModel.toString());
		
	}

}
