package com.ece.websocket.stomp;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

	/**
	 * Register STOMP endpoints mapping each to a specific URL and (optionally)
	 * enabling and configuring SockJS fallback options.
	 */
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/payws").setAllowedOriginPatterns("*");
		registry.addEndpoint("/payws-clientsock").setAllowedOriginPatterns("*").withSockJS();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		// Use an external broker (ActiveMQ)
		/* STOMP port of ActiveMQ 61613
		 * 
		 * 
		 */
		config.enableStompBrokerRelay("/topic", "/queue").setRelayHost("localhost").setRelayPort(61613) .setSystemLogin("admin").setSystemPasscode("adminPassword")
																										
																										
				.setClientLogin("admin").setClientPasscode("adminPassword");
		config.setApplicationDestinationPrefixes("/app");
		//config.setUserDestinationPrefix("/user");

	}

}
