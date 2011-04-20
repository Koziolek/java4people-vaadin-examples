package pl.koziolekweb.java4people.tabs;

import pl.koziolekweb.java4people.utils.Timer;

import com.vaadin.data.Validator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * W tej zakładce możesz sprawdzić jak będzie zachowywało się Vaadin pod kątem
 * wydajności. Test polega na wygenerowaniu tabeli o okreslonej ilości rekordów
 * i:
 * <ul>
 * <li>sukcesywnym jej dociąganiu</li>
 * <li>wyświetleniu w całości</li>
 * </ul>
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class PerformanceTab extends CustomComponent {

	private Table table;
	private VerticalLayout root;
	private Timer timer = new Timer();

	public PerformanceTab() {
		root = new VerticalLayout();
		setCompositionRoot(root);
		Label l1 = new Label("Ilość rekordów");
		root.addComponent(l1);
		final TextField numb = new TextField();
		root.addComponent(numb);
		numb.addValidator(new Validator() {

			public void validate(Object value) throws InvalidValueException {
				try {
					Integer.parseInt(value.toString());
				} catch (NumberFormatException e) {
					throw new InvalidValueException(e.getLocalizedMessage());
				}
			}

			public boolean isValid(Object value) {
				try {
					validate(value);
					return true;
				} catch (Exception e) {

				}
				return false;
			}
		});
		final CheckBox box = new CheckBox("Pokaż całość");
		root.addComponent(box);
		Button button = new Button("Generuj");
		button.addListener(new ClickListener() {

			public void buttonClick(ClickEvent event) {
				if (numb.isValid()) {
					timer.start();
					if (table != null) {
						root.removeComponent(table);
					}
					table = new Table("Test wydajność - ładowanie całości: "
							+ box.booleanValue());
					table.addContainerProperty("id", Long.class, null);
					table.addContainerProperty("name", String.class, null);
					int numRec = Integer.parseInt(numb.getValue().toString());
					for (long i = 0; i < numRec; i++) {
						table.addItem(new Object[] { i, "Kowalski " + i }, i);
					}

					if (box.booleanValue()) {
						table.setHeight(21 + numRec * 21, UNITS_PIXELS);
					}
					table.setWidth(300, UNITS_PIXELS);
					root.addComponent(table);
					timer.stop();

					System.out.println("Czas generowania na serwerze: "
							+ timer.getSeconds());
				}
			}
		});
		root.addComponent(button);

	}
}
