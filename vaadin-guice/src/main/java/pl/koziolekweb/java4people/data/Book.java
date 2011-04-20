package pl.koziolekweb.java4people.data;

public class Book {

	private Long id;

	private String title;

	private String author;

	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Book(Long id, String title, String author, String description) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
	}

	public Book() {

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Book))
			return false;
		Book b = (Book) obj;
		if(id == null)
			return false;
		return id.equals(b.id);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", description=" + description + "]";
	}
	
}
