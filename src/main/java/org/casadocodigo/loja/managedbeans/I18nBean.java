package org.casadocodigo.loja.managedbeans;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Model
public class I18nBean implements Serializable {

	@Inject
	private FacesContext context;

	public String changeLocale(String language) {
		context.getApplication().setDefaultLocale(new Locale(language));
		return "/site/index.xhtml?faces-redirect=true";
	}

}
