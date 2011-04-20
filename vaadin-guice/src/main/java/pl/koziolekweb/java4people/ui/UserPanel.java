package pl.koziolekweb.java4people.ui;

import pl.koziolekweb.java4people.data.User;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class UserPanel extends CustomComponent {

	private HorizontalLayout horizontalLayout;
	private Label loginCaption;
	private Label loginValue;
	private Button logout;

	public UserPanel() {
		horizontalLayout = new HorizontalLayout();
		setCompositionRoot(horizontalLayout);
		loginCaption = new Label("Login: ");
		loginValue = new Label();
		logout = new Button("logout");

		logout.addListener(new ClickListener() {
			
			public void buttonClick(ClickEvent event) {
				getApplication().close();
			}
		});
		
		horizontalLayout.setHeight(25, UNITS_PIXELS);
		horizontalLayout.addComponent(loginCaption);
		horizontalLayout.addComponent(loginValue);
		horizontalLayout.addComponent(logout );
		loginCaption.setWidth(100, UNITS_PIXELS);
		loginValue.setWidth(100, UNITS_PIXELS);
	}
	
	@Override
	public void attach() {
		super.attach();
	}

	public void setUser(User user) {
		if (user != null)
			loginValue.setValue(user.getLogin());
	}
}
