package com.codetruck.authuser.services.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.codetruck.authuser.models.UserModel;
import com.codetruck.authuser.repositories.UserRepository;
import com.codetruck.authuser.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Page<UserModel> findAll(Specification<UserModel> spec, Pageable pageable) {
		return this.userRepository.findAll(spec, pageable);
	}

	@Override
	public Optional<UserModel> findById(UUID userId) {
		return this.userRepository.findById(userId);
	}

}
