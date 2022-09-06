package com.codetruck.authuser.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.codetruck.authuser.models.UserModel;

public interface UserService {

	public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable);

	Optional<UserModel> findById(UUID userId);

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);

	public void save(UserModel userModel);

	public void delete(UserModel userModel);
	
}
