package com.ece.ativeMq.broker.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * This will send the payment status to the queue  ex:CBS will push the payment status
 */
@RestController
@RequestMapping("/payment-status")
public class PaymentStatusController {

	@Autowired
	private PaymentStatusService paymentStatusService;

	@GetMapping("/post-to-queue/{status}/{payid}")
	public String postSttausToQueue(@PathVariable("status") String status, @PathVariable("payid") String payid) {

		paymentStatusService.sendPaymentStatus("PAYMENT_STATUS_QUEUE", status, payid);
		return "Payment status posted to the queue successfully";
	}

}
