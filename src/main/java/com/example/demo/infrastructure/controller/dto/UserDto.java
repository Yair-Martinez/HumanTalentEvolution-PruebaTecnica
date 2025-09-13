package com.example.demo.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
	
	private Integer id;

	private String name;

	private String email;
}
