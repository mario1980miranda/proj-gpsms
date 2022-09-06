package com.codetruck.authuser.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetruck.authuser.models.RoleModel;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {

}
