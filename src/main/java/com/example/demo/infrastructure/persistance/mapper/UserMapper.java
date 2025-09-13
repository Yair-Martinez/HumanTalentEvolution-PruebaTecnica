package com.example.demo.infrastructure.persistance.mapper;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.request.UserRequest;
import com.example.demo.infrastructure.persistance.UserEntity;

public class UserMapper {

	public static UserEntity fromUserRequestToUserEntity(UserRequest userRequest) {
		return new UserEntity(null, userRequest.getName(), userRequest.getEmail());
	}

	public static User fromUserEntityToUser(UserEntity userEntity) {
		return new User(userEntity.getId(), userEntity.getName(), userEntity.getEmail());
	}
}
