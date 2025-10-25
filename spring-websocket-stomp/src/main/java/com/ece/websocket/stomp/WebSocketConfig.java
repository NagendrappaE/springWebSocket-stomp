package com.ece.websocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// Client connects to /ws (with SockJS fallback)
		registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// messages sent to destinations starting with /topic will be routed to
		// subscribers
		registry.enableSimpleBroker("/topic");
		// messages sent from client to /app will be routed to @MessageMapping handlers
		// (not used here)
		registry.setApplicationDestinationPrefixes("/app");
	}
}
