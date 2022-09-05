package com.codetruck.authuser.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.codetruck.authuser.models.UserModel;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {

	@And({		
		@Spec(path = "UserType", spec = Equal.class),
		@Spec(path = "UserStatus", spec = Equal.class),
		@Spec(path = "email", spec = Like.class)
	})
	public interface UserSpec extends Specification<UserModel> {}
}
