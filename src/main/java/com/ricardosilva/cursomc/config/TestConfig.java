package com.ricardosilva.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ricardosilva.cursomc.services.DBService;
import com.ricardosilva.cursomc.services.EmailService;
import com.ricardosilva.cursomc.services.MockEmailSercice;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dbService.instantiateTestDatabase();		
		return true;
	}

	@Bean
	public EmailService emailService() {
		return new MockEmailSercice();
	}
}
