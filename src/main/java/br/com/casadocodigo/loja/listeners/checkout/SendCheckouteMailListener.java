package br.com.casadocodigo.loja.listeners.checkout;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.casadocodigo.loja.daos.CheckoutDAO;
import org.casadocodigo.loja.infra.MailSender;
import org.casadocodigo.loja.models.Checkout;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/checkoutsTopic") })
public class SendCheckouteMailListener implements MessageListener {

	// private Logger logger = LoggerFactory.getLogger(SendCheckouteMailListener.class);
	@Inject
	private MailSender mailSender;
	@Inject
	private CheckoutDAO checkoutDao;

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
		Checkout checkout = checkoutDao
		.findByUuid(text.getText());
		String eMailBody = "<html><body>Compra realizada com sucesso. O código de acompanhamento é " + checkout.getUuid() + "</body></html>";
		mailSender.send("compras@casadocodigo.com.br",checkout.getBuyer().getEmail(),"Sua compra foi registrada com sucesso",eMailBody);
		} catch (Exception e) {
		// logger.error("Problema no envio do e-mail",e);
			System.err.println("Problema no envio do e-mail" + e.getMessage());
		}

	}

}
