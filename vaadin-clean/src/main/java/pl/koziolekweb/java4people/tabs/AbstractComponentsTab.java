package pl.koziolekweb.java4people.tabs;

import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;

/**
 * Abstrakcyjna klasa, która będzie nam służyć do tworzenia własnych zakładek.
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class AbstractComponentsTab extends CustomComponent {

	/**
	 * "Główny punkt zaczepienia" dla elementów komponentu.
	 */
	private AbstractOrderedLayout root;

	/**
	 * Tworząc własny komponent musimy wykonać pewne obowiązkowe czynności.
	 * 
	 * @param compositionRoot
	 */
	protected AbstractComponentsTab(AbstractOrderedLayout compositionRoot) {
		root = compositionRoot;
		// przede wszystkim wskazać korzeń drzewa komponentu.
		setCompositionRoot(compositionRoot);
	}

	/**
	 * Metoda pozwala nam na dodawanie elementów do naszego komponentu.
	 * Rozszerzając {@link CustomComponent} warto ją nadpisać by ułatwić sobie
	 * życie.
	 * 
	 * @param c
	 *            element do dodania.
	 * 
	 */
	@Override
	public void addComponent(Component c) {
		root.addComponent(c);
	}

	/**
	 * Metoda pozwala na usunięcie elementu z komponentu.
	 * 
	 * @param c
	 *            komponent do usunięcia.
	 */
	@Override
	public void removeComponent(Component c) {
		root.removeComponent(c);
	}
	
	protected AbstractLayout getRoot() {
		return root;
	}

	protected void addListener(LayoutClickListener layoutClickListener){
		root.addListener(layoutClickListener);		
	}
}