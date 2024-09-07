package com.blogapis.security.copy;

import com.blogapis.payloads.UserDto;

import lombok.Data;

@Data
public class JwtAuthResponce {

	private String token;
	private UserDto user;
}
