package com.johnbryce.cs.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.johnbryce.cs.exceptions.PurchaseUnavailableException;
import com.johnbryce.cs.exceptions.RestrictedEntityDetailsChangeException;


@ControllerAdvice
@RestController
public class CsControllerAdvice {
	
	@ExceptionHandler(value = {PurchaseUnavailableException.class, RestrictedEntityDetailsChangeException.class})
	public ResponseEntity<?> handleCustomException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}


}
