package com.ece.websocket.stomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloWorldController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@GetMapping("{id}/{status}")
	public String pushNotification(@PathVariable("id") String id, @PathVariable("status") String status) {

		messagingTemplate.convertAndSend("/topic/payment-status/" + id, status);

		return "pushed successfully";
	}

}
