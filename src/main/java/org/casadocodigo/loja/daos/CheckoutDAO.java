package org.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.models.Checkout;

public class CheckoutDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public void save(Checkout checkout) {
		entityManager.persist(checkout);
	}
	
	public Checkout findByUuid(String Uuid) {
		return entityManager.createQuery("SELECT c FROM Checkout c WHERE c.uuid = :uuid ", Checkout.class)
				.setParameter("uuid", Uuid).getSingleResult();
	}
}
