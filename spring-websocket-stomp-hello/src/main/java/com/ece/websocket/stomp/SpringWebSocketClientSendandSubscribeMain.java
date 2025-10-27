package com.ece.websocket.stomp;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;

/*
 * demonstrate that client send a message to server and subscribe to receive message from server
 */
 
public class SpringWebSocketClientSendandSubscribeMain {
	public static void main(String[] args) throws Exception {
		
        String url = "ws://localhost:8085/payws"; // Change port if needed

        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new StringMessageConverter());

        StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("Connected to WebSocket STOMP server!");

                // Subscribe to the same endpoint as the message mapping would return to
                session.subscribe("/topic/greeting", new StompFrameHandler() {
                    @Override
                    public Type getPayloadType(StompHeaders headers) {
                        return String.class;
                    }

                    @Override
                    public void handleFrame(StompHeaders headers, Object payload) {
                        System.out.println("Received: " + payload);
                    }
                });

                // Send a greeting to the server
                for(int i=0;i<5;i++)
                session.send("/app/greeting", "Hello from STOMP client!");
            }
        };

        stompClient.connect(url, sessionHandler).get();

        // Keep running to receive responses
        Thread.sleep(10000);
    }
}
