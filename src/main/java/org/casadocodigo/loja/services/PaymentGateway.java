package org.casadocodigo.loja.services;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

import org.casadocodigo.loja.models.PaymentData;

public class PaymentGateway {

	public PaymentGateway() {
		// TODO Auto-generated constructor stub
	}

	public void pay(BigDecimal total) {
		Client client = ClientBuilder.newClient();
		PaymentData paymentData = new PaymentData(total);
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		Entity<PaymentData> json = Entity.json(paymentData);
		client.target(uriToPay).request().post(json, String.class);
	}

}
