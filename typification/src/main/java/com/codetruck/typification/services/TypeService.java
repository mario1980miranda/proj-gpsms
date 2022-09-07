package com.codetruck.typification.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.codetruck.typification.models.TypeModel;

public interface TypeService {

	List<TypeModel> findAllParentTypes();

	Optional<TypeModel> findById(UUID typeId);

	long countParentTypes();

	void saveAll(List<TypeModel> asList);

}
