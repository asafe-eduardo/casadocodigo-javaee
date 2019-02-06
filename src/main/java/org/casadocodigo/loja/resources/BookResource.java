package org.casadocodigo.loja.resources;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.casadocodigo.loja.daos.BookDAO;
import org.casadocodigo.loja.models.Book;
import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;

@Path("books")
@Stateful
public class BookResource {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;
	private BookDAO bookDAO;

	@PostConstruct
	private void loadDAO() {
		this.bookDAO = new BookDAO(entityManager);
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Book> lastBooksJson() {
		// return bookDAO.lastReleases();
		return bookDAO.olderBooks(); // GETTING SOME RESULTS
	}
}