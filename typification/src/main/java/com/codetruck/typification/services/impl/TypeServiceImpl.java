package com.codetruck.typification.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.codetruck.typification.models.TypeModel;
import com.codetruck.typification.repositories.TypeRepository;
import com.codetruck.typification.services.TypeService;

@Service
public class TypeServiceImpl implements TypeService {

	final TypeRepository typeRepository;

	public TypeServiceImpl(TypeRepository typeRepository) {
		this.typeRepository = typeRepository;
	}

	@Override
	public List<TypeModel> findAllParentTypes() {
		return this.typeRepository.findAllParentTypes();
	}

	@Override
	public Optional<TypeModel> findById(UUID typeId) {
		return this.typeRepository.findById(typeId);
	}

	@Override
	public long countParentTypes() {
		return this.typeRepository.countParentTypes();
	}

	@Override
	public void saveAll(List<TypeModel> types) {
		this.typeRepository.saveAll(types);
	}
	
}
