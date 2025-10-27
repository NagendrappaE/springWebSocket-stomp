package com.ece.websocket.stomp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HelloWorldControllerMsg {

	/*
	 * Handles messages sent to /app/greeting.
	 * Returns a timestamped greeting as a response, which is sent only to the sender.
	 * If you want to broadcast to all subscribers of /topic/greeting, use messagingTemplate.convertAndSend instead.
	 */
	@MessageMapping("/greeting")
	public String handle(String greeting) {
		 log.info("Received greeting: {}", greeting);
		
		return "[" + getTimestamp() + ": " + greeting;
	}

	private String getTimestamp() {
		return new SimpleDateFormat("MM/dd/yyyy h:mm:ss a").format(new Date());
	}
}
