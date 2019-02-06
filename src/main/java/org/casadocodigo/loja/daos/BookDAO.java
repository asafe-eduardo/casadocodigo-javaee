package org.casadocodigo.loja.daos;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.casadocodigo.loja.models.Book;

@Stateful
public class BookDAO {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager manager;

	public BookDAO() {

	}

	public BookDAO(EntityManager manager) {
		this.manager = manager;
	}

	public void save(Book product) {
		manager.persist(product);
	}

	public Book findById(Integer id) {
		return manager.find(Book.class, id);
	}

	public List<Book> list() {
		return manager.createQuery("SELECT DISTINCT(b) FROM Book b JOIN FETCH b.authors", Book.class).getResultList();
	}

	public List<Book> lastReleases() {
		return manager.createQuery("SELECT b FROM Book b WHERE b.releaseDate <= now() ORDER BY b.id DESC", Book.class)
				.setHint("org.hibernate.cacheable", true)
				.setMaxResults(3).getResultList();
	}

	public List<Book> olderBooks() {
		return manager.createQuery("SELECT b FROM Book b", Book.class)
				.setHint("org.hibernate.cacheable", true)
				.setMaxResults(20).getResultList();
	}
}
