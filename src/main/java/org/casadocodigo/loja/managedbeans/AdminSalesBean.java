package org.casadocodigo.loja.managedbeans;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.casadocodigo.loja.models.Book;
import org.casadocodigo.loja.models.Sale;
import org.casadocodigo.loja.websockets.ConnectedUsers;

@Model
public class AdminSalesBean {

	private Sale sale = new Sale();
	@Inject
	private	ConnectedUsers	connectedUsers;
	
	@PostConstruct
	private void configure() {
		this.sale.setBook(new Book());
	}

	public String save() {
		// precisamos notificar os usuários sobre a promoção
		connectedUsers.send(sale.toJson());
		return "/admin/promocoes/form.xhtml?faces-redirect=true";
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

}
