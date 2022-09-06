package com.codetruck.authuser.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
	
	public interface UserView {
		public interface RegistrationPost {}
		public interface UserPut {}
		public interface PasswordPut {}
		public interface ImagePut {}
	}

	@NotBlank(groups = UserView.RegistrationPost.class)
	@Size(min = 4, max = 50, groups = UserView.RegistrationPost.class)
	@JsonView(UserView.RegistrationPost.class)
	private String username;
	
	@NotBlank(groups = UserView.RegistrationPost.class)
	@Email(groups = UserView.RegistrationPost.class)
	@Size(max = 100, groups = UserView.RegistrationPost.class)
	@JsonView(UserView.RegistrationPost.class)
	private String email;
	
	@NotBlank(groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
	@Size(min = 6, max = 20, groups = {UserView.RegistrationPost.class, UserView.PasswordPut.class})
	@JsonView({UserView.RegistrationPost.class, UserView.PasswordPut.class})
	private String password;
	
	@NotBlank(groups = UserView.PasswordPut.class)
	@Size(min = 6, max = 20, groups = UserView.PasswordPut.class)
	@JsonView(UserView.PasswordPut.class)
	private String oldPassword;
	
	@JsonView({UserView.RegistrationPost.class, UserView.UserPut.class})
	private String fullName;
	
	@NotBlank
	@JsonView(UserView.ImagePut.class)
	private String imageUrl;
}
