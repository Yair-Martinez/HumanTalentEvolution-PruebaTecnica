package com.example.demo.application.port.out;

import org.springframework.stereotype.Component;

import com.example.demo.domain.model.User;
import com.example.demo.infrastructure.controller.request.UserRequest;
import com.example.demo.infrastructure.persistance.SpringUserRepository;
import com.example.demo.infrastructure.persistance.UserEntity;
import com.example.demo.infrastructure.persistance.mapper.UserMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryPort {
	
	private final SpringUserRepository userRepository;

	@Override
	public User save(UserRequest userRequest) {
		UserEntity userEntity = UserMapper.fromUserRequestToUserEntity(userRequest);
		UserEntity userEntitySaved = userRepository.save(userEntity);
		
		return UserMapper.fromUserEntityToUser(userEntitySaved);
	}

}
