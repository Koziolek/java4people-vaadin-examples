package pl.koziolekweb.java4people.tabs;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Form;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Przykład zastosowania formularza bez bindowania danych.<br/>
 * 
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class FormComponentsTab extends AbstractComponentsTab {

	/**
	 * Formularz.
	 */
	private Form form;

	/*
	 * Różne rodzaje pól
	 */
	private TextField text;
	private PasswordField passwordField;
	private RichTextArea richTextArea;
	private DateField dateField;
	private CheckBox box;
	private Select select;

	public FormComponentsTab() {
		super(new VerticalLayout());
		form = new Form();
		form.setCaption("Prosty formularz");

		// tworzymy pola formularza
		text = new TextField("Pole tekstowe");
		passwordField = new PasswordField("Hasło");
		richTextArea = new RichTextArea("edytor");
		dateField = new DateField("data");
		dateField.setDateFormat("dd/MM/yyyy");
		box = new CheckBox("checkbox");
		select = new Select("prosta lista");
		select.addItem("pierwszy");
		select.addItem("drugi");

		buildFooter();

		// dodajemy pola do formularza. Pierwszy parametr to identyfikator
		// pozwalający na pobranie wartości bezpośrednio z formularza.
		form.addField("text", text);
		form.addField("password", passwordField);
		form.addField("rta", richTextArea);
		form.addField("data", dateField);
		form.addField("chb", box);
		form.addField("sel", select);
		addComponent(form);
	}

	/**
	 * Formularz nawygodniej wysłać budując odpowiednią stopkę. Pozwala to na
	 * zachowanie pełnej kontroli nad przetwarzaniem.
	 */
	private void buildFooter() {
		// pobieramy stopkę
		Layout footer = form.getFooter();
		// tworzymy przycisk i dodajemy do niego listener
		Button send = new Button("wyślij");
		send.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {

				// w listenerzep[obieramy pola formularza i wypisujemy do logu.
				// Spójrz na konsolę by zobczyć jak reprezentowane są
				// poszczególne pola.
				System.out.println(form.getItemProperty("text"));
				System.out.println(form.getItemProperty("password"));
				System.out.println(form.getItemProperty("rta"));
				System.out.println(form.getItemProperty("data"));
				System.out.println(form.getItemProperty("chb"));
				System.out.println(form.getItemProperty("sel"));
			}
		});
		footer.addComponent(send);
	}
}
