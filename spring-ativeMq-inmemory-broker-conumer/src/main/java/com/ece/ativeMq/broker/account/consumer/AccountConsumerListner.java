package com.ece.ativeMq.broker.account.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.Message;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AccountConsumerListner {

	@JmsListener(destination = "ACCOUNT_STATUS_QUEUE")
	public void consumeAccountStatus(Message payStatus) {
		// TODO Auto-generated method stub
		// process here further logic
		log.error("consumed message from Broker Queue {}", payStatus);

	}
}
