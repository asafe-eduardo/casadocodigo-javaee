package org.casadocodigo.loja.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.casadocodigo.loja.daos.AuthorDAO;
import org.casadocodigo.loja.daos.BookDAO;
import org.casadocodigo.loja.infra.FileSaver;
import org.casadocodigo.loja.infra.MessagesHelper;
import org.casadocodigo.loja.models.Author;
import org.casadocodigo.loja.models.Book;

@Model
public class AdminBooksBean {

	private Book product = new Book();

	private Part summary;
	
	private String summarypath;
	
	@Inject
	private AuthorDAO authorDAO;
	@Inject
	private BookDAO bookDAO;
	@Inject
	private MessagesHelper messagesHelper;
	@Inject
	private FileSaver fileSaver;
	
	private List<Integer> selectedAuthorsIds = new ArrayList<>();
	private List<Author> authors = new ArrayList<Author>();

	@PostConstruct
	public void loadObjects() {
		this.authors = authorDAO.list();
	}

	@Transactional
	public String save() {		
		
		fileSaver.write("summaries", summary);
		// populateBookAuthor();
		this.bookDAO.save(product);
		// clearObjects();
		
		messagesHelper.addFlash(new FacesMessage("Livro gravado com sucesso"));
		
		return "/livros/lista?faces-redirect=true";
	}

	private void populateBookAuthor() {
		selectedAuthorsIds.stream().map((id) -> {
			return new Author(id);
		}).forEach(product::addAuthor);
	}
	
	private void clearObjects() {
		this.product = new Book();
		this.selectedAuthorsIds.clear();
	}

	public Book getProduct() {
		return this.product;
	}

	public List<Integer> getSelectedAuthorsIds() {
		return selectedAuthorsIds;
	}

	public void setSelectedAuthorsIds(List<Integer> selectedAuthorsIds) {
		this.selectedAuthorsIds = selectedAuthorsIds;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public Part getSummary() {
		return summary;
	}

	public void setSummary(Part summary) {
		this.summary = summary;
	}

	public String getSummarypath() {
		return summarypath;
	}

	public void setSummarypath(String summarypath) {
		this.summarypath = summarypath;
	}
	
	
	

}
