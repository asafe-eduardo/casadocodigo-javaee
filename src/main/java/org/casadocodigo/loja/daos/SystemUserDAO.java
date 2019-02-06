package org.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.models.SystemUser;

public class SystemUserDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(SystemUser systemUser) {
		entityManager.persist(systemUser);
	}
}
