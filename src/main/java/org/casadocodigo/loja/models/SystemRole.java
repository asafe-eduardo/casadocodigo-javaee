package org.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class SystemRole {

	@Id
	private String name;

	@Deprecated // apenas para os frameworks
	public SystemRole() {
	}

	public SystemRole(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
