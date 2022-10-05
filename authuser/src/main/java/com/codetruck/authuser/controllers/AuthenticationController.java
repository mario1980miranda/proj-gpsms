package com.codetruck.authuser.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.authuser.dtos.UserDto;
import com.codetruck.authuser.enums.UserStatus;
import com.codetruck.authuser.enums.UserType;
import com.codetruck.authuser.models.UserModel;
import com.codetruck.authuser.services.UserService;
import com.fasterxml.jackson.annotation.JsonView;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

	final UserService userService;

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<Object> registerUser(
			@RequestBody
			@Validated(UserDto.UserView.RegistrationPost.class)
//			@JsonView(UserDto.UserView.RegistrationPost.class)
			UserDto userDto) {
		
		log.debug("POST registerUser UserDto {}", userDto.toString());
		
		if (this.userService.existsByUsername(userDto.getUsername())) {
			log.warn("Username {} is already taken.", userDto.getUsername());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Username is already taken!");
		}
		
		if (this.userService.existsByEmail(userDto.getEmail())) {
			log.warn("Email {} is already in use.", userDto.getEmail());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Email is already taken!");
		}
		
		var userModel = new UserModel();
		BeanUtils.copyProperties(userDto, userModel);
		userModel.setUserStatus(UserStatus.ACTIVE);
		userModel.setUserType(UserType.WIZARD);
		userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		this.userService.save(userModel);
		
		log.debug("UserModel saved: {}", userModel.toString());
		log.info("User saved successfully {}", userModel.toString());
		
		userModel.add(linkTo(methodOn(UserController.class).getOneUser(userModel.getUserId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}
}
