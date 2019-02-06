package org.casadocodigo.loja.managedbeans;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.casadocodigo.loja.daos.CheckoutDAO;
import org.casadocodigo.loja.daos.SystemUserDAO;
import org.casadocodigo.loja.models.Checkout;
import org.casadocodigo.loja.models.ShoppingCart;
import org.casadocodigo.loja.models.SystemUser;

@Model
@Path("payment")
public class CheckoutBean {
	private SystemUser systemUser = new SystemUser();
	@Inject
	private SystemUserDAO systemUserDAO;
	@Inject
	private CheckoutDAO checkoutDAO;
	@Inject
	private ShoppingCart cart;
	@Inject
	private FacesContext facesContext;
	
	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	@Transactional
	public void checkout() throws IOException {
		systemUserDAO.save(systemUser);

		Checkout checkout = new Checkout(systemUser, cart);
		checkoutDAO.save(checkout);

		ExternalContext externalContext = facesContext.getExternalContext();
		String contextName = externalContext.getContextName();
		
 		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		response.setStatus(307);
		response.setHeader("Location", "/" + contextName + "/services/payment?uuid=" + checkout.getUuid());
	}
}
