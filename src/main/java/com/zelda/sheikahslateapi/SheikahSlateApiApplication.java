package com.zelda.sheikahslateapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SheikahSlateApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SheikahSlateApiApplication.class, args);
		System.out.println("\n" + "------------------------------" + "\n" + "*** App is up and running ***" + "\n" + "------------------------------" + "\n");
	}

}
