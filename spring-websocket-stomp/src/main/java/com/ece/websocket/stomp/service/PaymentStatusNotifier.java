package com.ece.websocket.stomp.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import lombok.var;

@Service
public class PaymentStatusNotifier {

	@Autowired
    private  SimpMessagingTemplate messagingTemplate;
    
	/**
     * Broadcasts status update for a particular transactionId to /topic/payment-status/{transactionId}
     * Clients subscribe to that topic to receive real-time updates.
     */
    public void notifyStatus(String transactionId, String status, String message) {
        var payload = Map.of(
                "transactionId", transactionId,
                "status", status,
                "message", message
        );
        messagingTemplate.convertAndSend("/topic/payment-status/" + transactionId, payload);
    }
}
