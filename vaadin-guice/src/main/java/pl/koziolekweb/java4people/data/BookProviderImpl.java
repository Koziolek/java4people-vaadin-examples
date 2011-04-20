package pl.koziolekweb.java4people.data;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class BookProviderImpl implements BookProvider {

	private List<Book> books;
	
	private AtomicLong sequence;
	
	public BookProviderImpl() {
		books = new LinkedList<Book>();
		sequence = new AtomicLong(0L);
	}
	
	public void add(Book t) {
		if(books.contains(t)){
			merge(t);
		}
		else{
			if(t.getId()==null){
				t.setId(sequence.incrementAndGet());
			}
			books.add(t);
		}
	}

	public void delete(Book t) {
		if(books.contains(t))
			books.remove(t);
	}

	public Collection<Book> getAll() {
		return books;
	}

	private void merge(Book neew) {
		int index = books.indexOf(neew);
		books.add(index, neew);
	}
}
