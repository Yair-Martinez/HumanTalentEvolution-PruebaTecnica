package com.example.demo.infrastructure.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {

	private String name;

	private String email;
}
