package com.codetruck.typification.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.typification.dtos.TypeDto;
import com.codetruck.typification.models.TypeModel;
import com.codetruck.typification.services.TypeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/typification")
public class TypeController {
	
	final TypeService typeService;

	public TypeController(TypeService typeService) {
		this.typeService = typeService;
	}

	@GetMapping
	public ResponseEntity<List<TypeModel>> getAllParentTypes() {
		
		log.debug("GET getAllParentTypes");
		
		final List<TypeModel> typeModelList = this.typeService.findAllParentTypes();
		
		if (!typeModelList.isEmpty()) {
			for (TypeModel typeModel : typeModelList) {
				typeModel.add(linkTo(methodOn(TypeController.class).getOneType(typeModel.getTypeId())).withSelfRel());
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(typeModelList);
	}

	@GetMapping("/{typeId}")
	public ResponseEntity<Object> getOneType(
			@PathVariable(value = "typeId")
			UUID typeId) {

		log.debug("GET getOneType typeId", typeId);
		
		Optional<TypeModel> typeModelOptional = this.typeService.findById(typeId);
		
		if (!typeModelOptional.isPresent()) {
			log.warn("Type {} not found.", typeId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(typeModelOptional.get());
	}
	
	@PutMapping("/{typeId}/registerChild")
	public ResponseEntity<Object> registerType(
			@PathVariable(value = "typeId")
			UUID typeId,
			@RequestBody
			TypeDto typeDto) {

		log.debug("PUT registerType typeId", typeId);
		
		Optional<TypeModel> typeModelOptional = this.typeService.findById(typeId);
		
		if (!typeModelOptional.isPresent()) {
			log.warn("Type {} not found.", typeId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found.");
		}
		
		// montar o objeto filho associado ao parent
		
		return ResponseEntity.status(HttpStatus.CREATED).body(typeModelOptional.get());
	}
}
