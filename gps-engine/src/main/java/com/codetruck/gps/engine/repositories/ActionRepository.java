package com.codetruck.gps.engine.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codetruck.gps.engine.models.ActionModel;

public interface ActionRepository extends JpaRepository<ActionModel, UUID> {

}
