package org.casadocodigo.loja.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.casadocodigo.loja.models.SystemUser;

@Model
public class CurrentUser {

	@Inject
	private HttpServletRequest request;

	@Inject
	private SecurityDAO securityDAO;
	private SystemUser systemUser;

	public SystemUser get() {
		return this.systemUser;
	}

	public boolean hasRole(String name) {
		return request.isUserInRole(name);
	}

	@PostConstruct
	public void loadSystemUser() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			this.systemUser = securityDAO.loadUserByUsername(principal.getName());
		}
	}
}
