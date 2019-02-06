package org.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.casadocodigo.loja.models.Author;

public class AuthorDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Author> list(){
		return this.entityManager.createQuery("SELECT a FROM Author a").getResultList();
	}
	
}
