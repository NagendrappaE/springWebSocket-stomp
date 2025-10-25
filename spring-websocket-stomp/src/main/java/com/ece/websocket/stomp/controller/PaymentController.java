package com.ece.websocket.stomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	@GetMapping("/status/{transactionId}")
	public String showStatusPage(@PathVariable("transactionId") String transactionId, Model model) {
		model.addAttribute("transactionId", transactionId);
		
		return "payment-status";
	}

}
