package com.cucumber.tutorial.cucumber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CucumberApplication {

	public static void main(String[] args) {
		SpringApplication.run(CucumberApplication.class, args);
	}
}
