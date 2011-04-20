package pl.koziolekweb.java4people.data;

import java.util.Collection;

public interface DataProvider<T> {

	public void add(T t);
	
	public void delete(T t);
	
	public Collection<T> getAll();
	
}
