package com.ece.ativeMq.broker.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import jakarta.jms.Message;

@Service
public class PaymentStatusService {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void sendPaymentStatus(String queueName, String paymentStatus, String paymentId) {
		//jmsTemplate.convertAndSend(queueName, paymentStatus);

		jmsTemplate.send(queueName, session -> {

			Message payMessage = session.createTextMessage(paymentStatus);
			payMessage.setStringProperty("payId", paymentId);

			return payMessage;
		});

	}

}
