package pl.koziolekweb.java4people.ui;

import pl.koziolekweb.java4people.data.User;

import com.google.inject.Inject;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.LoginForm.LoginListener;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class LoginWindow extends Window {

	private LoginForm loginForm;

	@Inject
	private ApplicationWindow applicationWindow;

	public LoginWindow() {
		super("Vaadin Guice - Login");
		loginForm = new LoginForm();
		loginForm.setUsernameCaption("Login");
		loginForm.addListener(new LoginListener() {

			public void onLogin(LoginEvent event) {
				login(event.getLoginParameter("username"),
						event.getLoginParameter("password"));
			}
		});
		addComponent(loginForm);
	}

	private void login(String username, String password) {
		getApplication().setUser(new User(username));
		getApplication().setMainWindow(applicationWindow);
		open(new ExternalResource(getApplication().getURL()));
	}

	public void setTarget(ApplicationWindow applicationWindow) {
		this.applicationWindow = applicationWindow;
	}
}
