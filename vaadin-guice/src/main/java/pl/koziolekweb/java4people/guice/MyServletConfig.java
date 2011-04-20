package pl.koziolekweb.java4people.guice;

import pl.koziolekweb.java4people.MyVaadinApplication;
import pl.koziolekweb.java4people.data.BookProvider;
import pl.koziolekweb.java4people.data.BookProviderImpl;
import pl.koziolekweb.java4people.ui.ApplicationWindow;
import pl.koziolekweb.java4people.ui.BookForm;
import pl.koziolekweb.java4people.ui.BookTable;
import pl.koziolekweb.java4people.ui.LoginWindow;
import pl.koziolekweb.java4people.ui.UserPanel;
import pl.koziolekweb.java4people.ui.actions.BookDeleteListenerImpl;
import pl.koziolekweb.java4people.ui.actions.BookSaveListenerImpl;
import pl.koziolekweb.java4people.ui.actions.DeleteListener;
import pl.koziolekweb.java4people.ui.actions.SaveListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.inject.servlet.ServletScopes;
import com.vaadin.Application;

public class MyServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {

		ServletModule module = new ServletModule() {
			@Override
			protected void configureServlets() {
				serve("/*").with(GuiceApplicationServlet.class);

				bind(BookTable.class).in(ServletScopes.SESSION);
				bind(BookForm.class).in(ServletScopes.SESSION);
				bind(ApplicationWindow.class).in(ServletScopes.SESSION);
				bind(LoginWindow.class).in(ServletScopes.SESSION);
				bind(UserPanel.class).in(ServletScopes.SESSION);
				bind(DeleteListener.class).annotatedWith(
						Names.named("bookDeleteListener")).to(
						BookDeleteListenerImpl.class);
				bind(SaveListener.class).annotatedWith(
						Names.named("bookSaveListener")).to(
						BookSaveListenerImpl.class);
				bind(BookProvider.class).to(BookProviderImpl.class).in(
						ServletScopes.SESSION);
				bind(Application.class).to(MyVaadinApplication.class).in(
						ServletScopes.SESSION);
			}
		};

		Injector injector = Guice.createInjector(module);

		return injector;
	}
}