package pl.koziolekweb.java4people.tabs;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;

/**
 * W tej klasie umieściłem inne komponenty listy i tabele.
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class OtherComponentsTab extends AbstractComponentsTab {

	/**
	 * Prosta tabela.
	 */
	private Table table;

	/**
	 * Drzewko.
	 */
	private Tree tree;

	public OtherComponentsTab() {
		super(new HorizontalLayout());

		table = new Table("Tabela");
		table.addContainerProperty("ID", String.class, "");
		table.addContainerProperty("Name", String.class, "");
		table.addItem(new String[] { "1", "Kowalski" }, 1);
		table.addItem(new String[] { "2", "Nowak" }, 2);
		addComponent(table);

		tree = new Tree("Drzewka");
		Map<String, String[]> values = new LinkedHashMap<String, String[]>();
		values.put("JVM", new String[] { "java", "scala", "groovy" });
		values.put(".NOT", new String[] { "C#", "ASP", "VBS" });
		values.put("CGI", new String[] { "perl", "php" });
		values.put("ruby", null);
		Set<Entry<String, String[]>> entries = values.entrySet();
		for (Entry<String, String[]> entry : entries) {
			tree.addItem(entry.getKey());
			tree.setChildrenAllowed(entry.getKey(), false);
			if (entry.getValue() != null && entry.getValue().length > 0) {
				tree.setChildrenAllowed(entry.getKey(), true);
				for (String s : entry.getValue()) {
					tree.addItem(s);
					tree.setParent(s, entry.getKey());
					tree.setChildrenAllowed(s, false);
				}
			}
		}
		addComponent(tree);

	}

}
