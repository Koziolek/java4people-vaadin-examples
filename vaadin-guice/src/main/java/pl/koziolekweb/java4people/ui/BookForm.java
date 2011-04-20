package pl.koziolekweb.java4people.ui;

import pl.koziolekweb.java4people.data.Book;
import pl.koziolekweb.java4people.ui.actions.DeleteListener;
import pl.koziolekweb.java4people.ui.actions.SaveListener;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.Layout;

@SuppressWarnings("serial")
public class BookForm extends Form {

	private Button save;

	private Button delete;

	public BookForm() {
		setCaption("Add book");

		BeanItem<Book> item = new BeanItem<Book>(new Book(null, "", "", ""));
		setItemDataSource(item);

		blockEditionOfId();

		Layout footer = getFooter();
		save = new Button("Save");
		delete = new Button("Delete");

		footer.addComponent(save);
		footer.addComponent(delete);
	}

	@Inject
	public void addDeleteListener(
			@Named(value = "bookDeleteListener") DeleteListener deleteListener) {
		delete.addListener(deleteListener);
	}

	@Inject
	public void addSaveListener(
			@Named(value = "bookSaveListener") 
			SaveListener saveListener) {
		save.addListener(saveListener);
	}

	public Book getBook() {
		String id = null;
		if (getField("id").getValue() != null)
			id = getField("id").getValue().toString();

		String title = getField("title").getValue().toString();
		String author = getField("author").getValue().toString();
		String description = getField("description").getValue().toString();

		Book b = new Book(id == null ? null : new Long(id), title, author,
				description);
		return b;
	}

	public void reset() {
		BeanItem<Book> item = new BeanItem<Book>(new Book(null, "", "", ""));
		setItemDataSource(item);
		blockEditionOfId();

	}

	public void setBook(Book book) {
		if (book == null)
			return;
		BeanItem<Book> item = new BeanItem<Book>(book);
		setItemDataSource(item);
		blockEditionOfId();
	}

	private void blockEditionOfId() {
		Field field = getField("id");
		field.setEnabled(false);
	}

}
