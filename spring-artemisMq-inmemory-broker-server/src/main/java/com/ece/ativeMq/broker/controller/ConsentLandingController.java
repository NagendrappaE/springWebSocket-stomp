package com.ece.ativeMq.broker.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.jms.Message;

@Controller
@RequestMapping("/consent-landing")
public class ConsentLandingController {

	@Autowired
	JmsTemplate jmsTemplate;

	/*
	 * open finance ,on user completes authentication and autherization ,the API hub
	 * will redirect to below url
	 */
	@GetMapping("/{code}/{state}")
	public String loadConsentLandingPage(@PathVariable("code") String code, @PathVariable("state") String state) {

		// called token end point
		String ststus = " SUCCESS";

		jmsTemplate.send("CONSENT_LANDING_QUEUE", session -> {
			// Create a message to send to the queue
			String messageContent = "Authorization Code: " + code + ", State: " + state + "authStats" + ststus;

			Message message = session.createTextMessage(messageContent);

			message.setStringProperty("consentId", UUID.randomUUID().toString());

			return message;
		});

		// You can call token API and post the Message to Queue called
		// CONSENT_LANDING_QUEUE
		return "consent-landing";
	}

}
