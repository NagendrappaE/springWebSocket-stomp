package com.ece.websocket.stomp.bean;

import lombok.Data;

@Data
public class PaymentStatusEvent {
	  private String transactionId;
	    private String status;
	    private String message;
}
