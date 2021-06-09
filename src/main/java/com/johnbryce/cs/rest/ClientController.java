package com.johnbryce.cs.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class ClientController {

	abstract ResponseEntity<?> login(String email, String password);
}
