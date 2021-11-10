package com.zelda.sheikahslateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SheikahSlateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheikahSlateApiApplication.class, args);
		System.out.println("\n" + "------------------------------" + "\n" + "*** App is up and running ***" + "\n" + "------------------------------" + "\n");
	}

}
