package com.johnbryce.cs.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.johnbryce.cs.security.UserData;

@Configuration
public class TokenConfig {
	@Bean
	public Map<String, UserData> map(){
		return new HashMap<>();
	}
}
