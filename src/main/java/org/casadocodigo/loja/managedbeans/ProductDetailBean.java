package org.casadocodigo.loja.managedbeans;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.casadocodigo.loja.daos.BookDAO;
import org.casadocodigo.loja.models.Book;

@Model
@Stateful
public class ProductDetailBean {

	@PersistenceContext(type=PersistenceContextType.EXTENDED)
	private EntityManager manager;
	
	private BookDAO bookDAO;
	private Book book;
	private Integer id;

	@PostConstruct
	public void loadManager() {
		this.bookDAO = new BookDAO(manager);
	}
	
	public void loadBook() {
		this.book = bookDAO.findById(id);
	}

	public Book getBook() {
		return book;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
