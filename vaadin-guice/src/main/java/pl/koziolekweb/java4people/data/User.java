package pl.koziolekweb.java4people.data;

public class User {

	private String login;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public User(String login) {
		super();
		this.login = login;
	}
	
	public User() {

	}
	
}
