package pl.koziolekweb.java4people.ui;

import java.util.Collection;

import pl.koziolekweb.java4people.data.Book;
import pl.koziolekweb.java4people.data.BookProvider;
import pl.koziolekweb.java4people.data.User;

import com.google.inject.Inject;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class ApplicationWindow extends Window {

	@Inject
	private UserPanel userPanel;

	@Inject
	private BookProvider bookProvider;

	@Inject
	private BookTable books;

	@Inject
	private BookForm bookForm;

	public ApplicationWindow() {
		super("Vaadin guice - books");
	}

	@Override
	public void attach() {
		super.attach();
		init();
	}

	public void init() {
		Collection<Book> all = bookProvider.getAll();
		books.addAll(all);
		books.addListener(new Property.ValueChangeListener() {
			public void valueChange(ValueChangeEvent event) {
				Book selectedBook = books.getSelectedBook();
				bookForm.setBook(selectedBook);
			}
		});
		userPanel.setUser((User) getApplication().getUser());
		addComponent(userPanel);
		addComponent(books);
		addComponent(bookForm);
	}
}
