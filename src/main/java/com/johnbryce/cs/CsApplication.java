package com.johnbryce.cs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsApplication.class, args);
		System.out.println("IoC container was loaded");
	}


}
