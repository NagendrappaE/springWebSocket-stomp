/**
 * 
 */
package com.ece.websocket.stomp;

/**
 * 
 */
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
public class RedControllerStompClientNosubscription {

	 public static void main(String[] args) throws Exception {
	        String url = "ws://localhost:8085/payws"; // Change port if needed

	        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
	        stompClient.setMessageConverter(new StringMessageConverter());

	        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
	            @Override
	            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
	                System.out.println("Connected to server!");

	                // Send a message to /app/red/blue.123, where "123" will be mapped to @DestinationVariable String green
	                session.send("/app/red/blue.123", "Hello, Green variable!");
	                // Since your server method is void and doesn't return anything, no subscription is needed
	            }
	        };

	        stompClient.connect(url, sessionHandler).get();

	        // Keep alive for a few seconds to ensure message delivery
	        Thread.sleep(5000);
	    }
}
