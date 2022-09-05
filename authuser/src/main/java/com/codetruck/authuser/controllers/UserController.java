package com.codetruck.authuser.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.authuser.models.UserModel;
import com.codetruck.authuser.services.UserService;
import com.codetruck.authuser.specifications.SpecificationTemplate;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<Page<UserModel>> getAllUsers(
			SpecificationTemplate.UserSpec spec,
			@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.ASC)
			Pageable pageable) {
		
		log.debug("GET getAllUsers (UserSpec: {}, Pageable: {}", spec, pageable);
		
		Page<UserModel> userModelPage = this.userService.findAll(spec, pageable);
		
		if (!userModelPage.isEmpty()) {
			for (UserModel user : userModelPage.toList()) {
				user.add(linkTo(methodOn(UserController.class).getOneUser(user.getUserId())).withSelfRel());
			}
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId")UUID userId) {
		
		log.debug("GET getOneUser userId {}", userId);
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}
	
}
