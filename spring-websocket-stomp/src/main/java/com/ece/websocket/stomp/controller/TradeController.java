package com.ece.websocket.stomp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trade")
public class TradeController {

	@GetMapping("")
	public String showStatusPage( Model model) {
		//model.addAttribute("transactionId", transactionId);
		
		return "trade-status";
	}

}
