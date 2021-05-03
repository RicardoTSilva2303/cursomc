package com.ricardosilva.cursomc.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailSercice extends AbstractEmailService {
	
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailSercice.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando o envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("E-mail enviado!");
	}

}