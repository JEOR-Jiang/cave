package com.jeor.cave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaveApplication {

	public static void main(String[] args) {
		System.out.println("starting cave.");
		SpringApplication.run(CaveApplication.class, args);
	}

}
