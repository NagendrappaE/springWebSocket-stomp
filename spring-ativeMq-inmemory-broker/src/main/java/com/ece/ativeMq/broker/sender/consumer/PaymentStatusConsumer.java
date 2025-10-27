package com.ece.ativeMq.broker.sender.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import jakarta.jms.Message;
import lombok.extern.slf4j.Slf4j;

/*
 * This will consume the payment status from the queue  ex:CRM will consume the payment status
 */
@Component
@Slf4j
public class PaymentStatusConsumer {

	@JmsListener(destination = "PAYMENT_STATUS_QUEUE")
	public void consumePaymentStatus(Message payStatus) {
		// TODO Auto-generated method stub
		//process here further logic
		log.error("consumed message from Broker Queue {}", payStatus);
		

	}

}
