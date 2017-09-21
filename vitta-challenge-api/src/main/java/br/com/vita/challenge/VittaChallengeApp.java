package br.com.vita.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class VittaChallengeApp {

	public static void main(String[] args) {
		SpringApplication.run(VittaChallengeApp.class, args);
	}
}
