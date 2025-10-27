package com.ece.websocket.stomp.controller;

import java.security.Principal;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.ece.websocket.stomp.bean.Trade;
import com.ece.websocket.stomp.bean.TradeResult;

@Controller
public class PortfolioController {
	@MessageMapping("/trade")
	@SendToUser("/queue/position-updates")
	public TradeResult executeTrade(Trade trade, Principal principal) {
		
		 String username = (principal != null) ? principal.getName() : "anonymous";
		  // Your business logic here
	    TradeResult tradeResult = new TradeResult();
	    tradeResult.setStatus("SUCCESS");
	    tradeResult.setDetails("Trade executed for user: " + username +
	        ", Symbol: " + trade.getSymbol() +
	        ", Quantity: " + trade.getQuantity() +
	        ", Type: " + trade.getType());
	    return tradeResult; // <-- This will be sent to the user
		
	}
}
