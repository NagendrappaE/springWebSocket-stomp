package com.ece.websocket.stomp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ece.websocket.stomp.bean.PaymentStatusEvent;
import com.ece.websocket.stomp.service.PaymentStatusNotifier;


@RestController
@RequestMapping("/lfi/webhook")
public class WebhookController {
	
	@Autowired
	private PaymentStatusNotifier notifier;

	
	

	/**
     * Simulated webhook endpoint that LFI would call to deliver payment outcome.
     * Accepts JSON like:
     * {
     *   "transactionId": "txn-123",
     *   "status": "SUCCESS",
     *   "message": "Payment completed"
     * }
     */
    @PostMapping("/payment")
    public ResponseEntity<String> handlePaymentCallback(@RequestBody PaymentStatusEvent event) {
        // In real app: validate signature, update DB, audit, etc.
        System.out.println("Received webhook: " + event.getTransactionId() + " -> " + event.getStatus());

        // Notify connected clients via WebSocket
        notifier.notifyStatus(event.getTransactionId(), event.getStatus(), event.getMessage());

        return ResponseEntity.ok("received");
    }
    
}
