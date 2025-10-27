package com.ece.websocket.stomp;

import java.lang.reflect.Type;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import com.ece.websocket.stomp.bean.Trade;
import com.ece.websocket.stomp.bean.TradeResult;

public class TradeStompClientMain {
	public static void main(String[] args) throws Exception {
		String url = "ws://localhost:8085/payws"; // Change port/path if needed

		WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
	stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    //    stompClient.setMessageConverter(new StringMessageConverter());


		StompSessionHandler sessionHandler = new StompSessionHandlerAdapter() {
			@Override
			public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
				System.out.println("Connected!");

				// Subscribe to personal position updates (Spring automatically routes based on
				// authenticated user)
				session.subscribe("/user/queue/position-updates", new StompFrameHandler() {
					@Override
					public Type getPayloadType(StompHeaders headers) {
						return TradeResult.class; // Your result type
					}

					@Override
					public void handleFrame(StompHeaders headers, Object payload) {
						System.out.println("Received position update: " + payload);
					}
				});

				// Optionally: send a trade if your controller supports it
				Trade trade = new Trade();
				trade.setUserName("NagendrappaE-evolvus"); // must match what server expects!
				trade.setSymbol("AAPL");
				trade.setQuantity(10);
				trade.setType("BUY");
				session.send("/app/trade", trade);
			}
		};

		stompClient.connect(url, sessionHandler).get();
		Thread.sleep(100000); // Keep alive to receive messages
	}
}
