package org.casadocodigo.loja.managedbeans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.casadocodigo.loja.daos.BookDAO;
import org.casadocodigo.loja.models.Book;

@Model
public class HomeBean {
	@Inject
	private BookDAO bookDao;

	public List<Book> lastReleases() {
		return bookDao.lastReleases();
	}

	public List<Book> olderBooks() {
		return bookDao.olderBooks();
	}
}
