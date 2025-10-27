package com.ece.ativeMq.broker.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.jms.Message;

@RestController
@RequestMapping("/account-status")
public class AccountStatusController {

	@Autowired
	private JmsTemplate jmsTemplate;

	@GetMapping("/post-to-queue/{status}/{accountId}")
	public void sendAccountStatus(@PathVariable("status") String accountStatus,@PathVariable("accountId")  String accountId) {

		String queueName = "ACCOUNT_STATUS_QUEUE";
		jmsTemplate.send(queueName, session -> {

			Message accMessage = session.createTextMessage(accountStatus);
			accMessage.setStringProperty("accountId", accountId);

			return accMessage;
		});
	}

}
