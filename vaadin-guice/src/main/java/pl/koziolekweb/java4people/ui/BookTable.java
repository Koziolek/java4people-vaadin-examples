package pl.koziolekweb.java4people.ui;

import java.util.Collection;

import pl.koziolekweb.java4people.data.Book;

import com.vaadin.data.Item;
import com.vaadin.ui.Table;

@SuppressWarnings("serial")
public class BookTable extends Table {

	public BookTable() {
		super("Books");
		setSelectable(true);
		setImmediate(true);
		addContainerProperty("id", Long.class, null);
		addContainerProperty("title", String.class, null);
		addContainerProperty("author", String.class, null);
		addContainerProperty("description", String.class, null);
	}

	public void addAll(Collection<Book> books) {
		for (Book b : books) {
			addBook(b);
		}
	}

	public void addBook(Book b) {
		if (!containsId(b.getId()))
			addItem(new Object[] { b.getId(), b.getTitle(), b.getAuthor(),
					b.getDescription() }, b.getId());
		else {
			Item item = getItem(b.getId());
			item.getItemProperty("id").setValue(b.getId());
			item.getItemProperty("title").setValue(b.getTitle());
			item.getItemProperty("author").setValue(b.getAuthor());
			item.getItemProperty("description").setValue(b.getDescription());
		}
	}

	public Book getSelectedBook() {
		Object selectedValue = getValue();
		if (selectedValue == null)
			return null;

		Item item = getItem(selectedValue);
		Long id = new Long(item.getItemProperty("id").toString());
		String title = item.getItemProperty("title").toString();
		String author = item.getItemProperty("author").toString();
		String description = item.getItemProperty("description").toString();

		Book book = new Book(id, title, author, description);
		return book;
	}

	public void removeBook(Book b) {
		if (containsId(b.getId()))
			removeItem(b.getId());
	}
}
