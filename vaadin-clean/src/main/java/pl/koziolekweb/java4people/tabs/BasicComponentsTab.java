package pl.koziolekweb.java4people.tabs;

import java.util.Date;

import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

/**
 * Klasa zawiera przykładowe podstawowe komponenty Vaadin. Jest to też przykład
 * implementacji własnego komponentu Vaadin jako rozszerzenia klasy
 * {@link CustomComponent} (za pośrednictwem abstrakcyjnej
 * {@link AbstractComponentsTab}).
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class BasicComponentsTab extends AbstractComponentsTab {

	/**
	 * Prosty napis.
	 */
	private Label label;

	/**
	 * Prosty przycisk.
	 */
	private Button button;

	/**
	 * Prosty link.
	 */
	private Link link;

	/**
	 * Konstruktor musi wykonać pewne akcje.
	 * 
	 * @see AbstractComponentsTab
	 */
	public BasicComponentsTab() {
		super(new VerticalLayout());
		label = new Label("Etykieta");
		button = new Button("Przycisk");

		// Dodajemy prosty listener. W etykiecie wyświetli aktualny czas.
		button.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				label.setValue("Teraz mamy: " + new Date());
			}
		});

		link = new Link("Przykładowy link", new ExternalResource(
				"http://koziolekweb.pl"));

		// dodajemy elementy do komponentu.
		addComponent(label);
		addComponent(link);
		addComponent(button);
	}

}
