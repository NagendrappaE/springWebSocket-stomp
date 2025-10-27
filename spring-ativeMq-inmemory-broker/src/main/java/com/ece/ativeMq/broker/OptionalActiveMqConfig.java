package com.ece.ativeMq.broker;

import java.util.Arrays;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.security.AuthenticationUser;
import org.apache.activemq.security.SimpleAuthenticationPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OptionalActiveMqConfig {
	@Bean
	public BrokerService broker() throws Exception {
		BrokerService broker = new BrokerService();
		broker.setBrokerName("MyEmbeddedBroker"); // Set a custom broker name
		broker.setPersistent(true); // Enable disk persistence
		broker.setDataDirectory("/home/nagendrappae/Documents/MQ"); // Set custom data directory
		
		broker.addConnector("tcp://localhost:61616");        // OpenWire (JMS)
		broker.addConnector("stomp://localhost:61613");      // STOMP
		broker.addConnector("mqtt://localhost:1883");        // MQTT
		broker.addConnector("amqp://localhost:5672");        // AMQP
		//broker.addConnector("ws://localhost:61614");         // WebSocket
		
		// broker.addConnector("http://localhost:8161"); // Web console (ActiveMQ 5.x
		// only)
	//	broker.setUseJmx(true); // Enable JMX monitoring
		
		// Set up authentication
		SimpleAuthenticationPlugin authenticationPlugin = new SimpleAuthenticationPlugin();
		authenticationPlugin.setUsers(Arrays.asList(
		    new AuthenticationUser("admin", "adminPassword", "admins"), // username, password, group
		    new AuthenticationUser("user", "userPassword", "users")
		));
		broker.setPlugins(new org.apache.activemq.broker.BrokerPlugin[] { authenticationPlugin });
		
		
		broker.start(); // Start the broker
		return broker;
	}

}
