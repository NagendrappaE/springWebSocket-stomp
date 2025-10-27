package com.ece.websocket.stomp;

import java.lang.reflect.Type;

import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class SpringWebSocketClientSubscribeMain {
	public static void main(String[] args) throws Exception {

		System.out.println("Spring WebSocket STOMP Client Main");

		String url = "ws://localhost:8085/payws"; // Use /payws for native WebSocket endpoint

		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
		stompClient.setMessageConverter(new StringMessageConverter());

		
		
		StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
			@Override
			public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
				System.out.println("Connected to WebSocket STOMP server!");

				
				// Subscribe to topic for id=123
				session.subscribe("/topic/payment-status/123", new StompFrameHandler() {
					@Override
					public Type getPayloadType(StompHeaders headers) {
						return String.class;
					}

					@Override
					public void handleFrame(StompHeaders headers, Object payload) {
						System.out.println("Received status 123: " + payload);
					}
				});
				session.subscribe("/topic/payment-status/124", new StompFrameHandler() {
					@Override
					public Type getPayloadType(StompHeaders headers) {
						return String.class;
					}

					@Override
					public void handleFrame(StompHeaders headers, Object payload) {
						System.out.println("Received status 124: " + payload);
					}
				});
			}
		};

		StompSession session = stompClient.connect(url, sessionHandler).get();

		// Keep running to receive messages
		Thread.sleep(60000); // Keep client alive for 60 seconds
		session.disconnect();

	}
}
