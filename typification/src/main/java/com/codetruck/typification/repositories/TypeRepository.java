package com.codetruck.typification.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.codetruck.typification.models.TypeModel;

public interface TypeRepository extends JpaRepository<TypeModel, UUID> {

	@Query(value = "SELECT * FROM TB_TYPE WHERE PARENT_TYPE_ID is null", nativeQuery = true)
	List<TypeModel> findAllParentTypes();

	@Query(value = "SELECT count(1) FROM TB_TYPE WHERE PARENT_TYPE_ID is null", nativeQuery = true)
	long countParentTypes();

}
