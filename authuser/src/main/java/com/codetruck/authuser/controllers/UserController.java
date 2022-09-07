package com.codetruck.authuser.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codetruck.authuser.dtos.UserDto;
import com.codetruck.authuser.dtos.UserDto.UserView;
import com.codetruck.authuser.dtos.UserRolesDto;
import com.codetruck.authuser.models.UserModel;
import com.codetruck.authuser.services.UserService;
import com.codetruck.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

	final UserService userService;

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
	public ResponseEntity<Object> getOneUser(
			@PathVariable(value = "userId")
			UUID userId) {
		
		log.debug("GET getOneUser userId {}", userId);
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		final UserModel userModelFound = userModelOptional.get();
		userModelFound.add(linkTo(methodOn(UserController.class).getOneUser(userModelFound.getUserId())).withSelfRel());
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelFound);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUser(
			@PathVariable(value = "userId")
			UUID userId) {
		
		log.debug("DELETE deleteUser userId {}", userId);
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		this.userService.delete(userModelOptional.get());
		log.info("User {} deleted successfully.", userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<Object> updateUser(
			@PathVariable(value = "userId")
			UUID userId,
			@RequestBody
			@Validated(UserView.UserPut.class)
			@JsonView(UserView.UserPut.class)
			UserDto userDto) {
		
		log.debug("PUT updateUser userId {}, userDto {}", userId, userDto.toString());
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		var userModel = userModelOptional.get();
		userModel.setFullName(userDto.getFullName());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		this.userService.save(userModel);
		log.debug("User updated successfully {}", userModel.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}
	
	@PutMapping("/{userId}/password")
	public ResponseEntity<Object> updatePassword(
			@PathVariable(value = "userId")
			UUID userId,
			@RequestBody
			@Validated(UserView.PasswordPut.class)
			@JsonView(UserView.PasswordPut.class)
			UserDto userDto) {
		
		log.debug("PUT updatePassword userId {}, userDto {}", userId, userDto.toString());
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		if (!userModelOptional.get().getPassword().equals(userDto.getOldPassword())) {
			log.warn("Mismatched old password.");
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error: Mismatched old password.");
		}
		
		var userModel = userModelOptional.get();
		userModel.setPassword(userDto.getPassword());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		this.userService.save(userModel);
		log.debug("User`s password updated successfully {}", userModel.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}
	
	@PutMapping("/{userId}/image")
	public ResponseEntity<Object> updateImage(
			@PathVariable(value = "userId")
			UUID userId,
			@RequestBody
			@Validated(UserView.ImagePut.class)
			@JsonView(UserView.ImagePut.class)
			UserDto userDto) {
		
		log.debug("PUT updateImage userId {}, userDto {}", userId, userDto.toString());
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		var userModel = userModelOptional.get();
		userModel.setImageUrl(userDto.getImageUrl());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		this.userService.save(userModel);
		log.debug("User`s image updated successfully {}", userModel.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
	}

	@GetMapping("/{userId}/roles")
	public ResponseEntity<Object> getUserRoles(
			@PathVariable(value = "userId") UUID userId) {
		
		log.debug("GET getUserRoles userId {}", userId);
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
			
		final UserModel userModelFound = userModelOptional.get();
		
		userModelFound.add(linkTo(methodOn(UserController.class).getOneUser(userModelFound.getUserId())).withSelfRel());
		
		log.debug("User roles {}", userModelFound.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(userModelFound);
	}
	
	@PutMapping("/{userId}/roles")
	public ResponseEntity<Object> manageUserRoles(
			@PathVariable(value = "userId") UUID userId,
			@RequestBody
			@Validated
			UserRolesDto userRolesDto) {
		
		log.debug("PUT manageUserRoles userId {}, userRolesDto {}", userId, userRolesDto.toString());
		
		Optional<UserModel> userModelOptional = this.userService.findById(userId);
		
		if (!userModelOptional.isPresent()) {
			log.warn("User not found {}", userId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		var userModel = userModelOptional.get();
		userModel.setRoles(userRolesDto.getRoles());
		userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
		
		this.userService.save(userModel);

		userModel.add(linkTo(methodOn(UserController.class).getOneUser(userModel.getUserId())).withSelfRel());
		
		log.debug("User roles updated successfully {}", userModel.toString());
		
		return ResponseEntity.status(HttpStatus.OK).body(userModel);
	}
	
}
