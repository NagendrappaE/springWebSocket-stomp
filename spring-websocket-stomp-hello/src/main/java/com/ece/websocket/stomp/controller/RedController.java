package com.ece.websocket.stomp.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@MessageMapping("red")
@Slf4j
public class RedController {
/*
 * this is not returing anything so no SUBCRIBER
 */
	
	@MessageMapping("blue.{green}")
	public void handleGreen(@DestinationVariable("green") String green) {
		
		log.error("Received green value: {}", green);
		
	}
}
