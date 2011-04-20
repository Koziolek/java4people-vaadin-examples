package pl.koziolekweb.java4people.tabs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import org.vaadin.peter.contextmenu.ContextMenu;
import org.vaadin.peter.contextmenu.ContextMenu.ContextMenuItem;

import pl.koziolekweb.java4people.custom.Uploader;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Table;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * W tej zakładce prezentuję dwa różne podejścia do tworzenia i używania
 * niestandarodowych komponentów.<br />
 * W pierwszym wypadku {@link Uploader} jest komponentem rozszerzającym
 * {@link CustomComponent} dzięki czemu nie ma potrzeby uruchamiania kompilatora
 * GWT w celu dołaczenia komponentu do aplikacji.<br />
 * W drugim przypadku używam niestandardowych komponentów pobranych z <a
 * href="http://vaadin.com/directory">repozytorium</a> dodatków Vaadin.
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class CustomComponentsTab extends AbstractComponentsTab {

	private static final String TARGET = "./target/";
	private ContextMenu contextMenu;
	private Uploader uploader;
	private Table fileTable;
	private ContextMenuItem lookup;
	private ContextMenuItem delete;

	public CustomComponentsTab() {
		super(new VerticalLayout());
		uploader = new Uploader(TARGET);
		addComponent(uploader);
		uploader.addListener(new SucceededListener() {

			public void uploadSucceeded(SucceededEvent event) {
				String fileName = event.getFilename();
				long fileLength = event.getLength();
				Date modificationDate = new Date(new File(fileName)
						.lastModified());
				fileTable.addItem(new Object[] { fileName, fileLength,
						modificationDate }, fileName);
			}
		});
		fileTable = new Table("Lista załadowanych plików");
		fileTable.addContainerProperty("name", String.class, null,
				"Nazwa Pliku", null, null);
		fileTable.addContainerProperty("size", Long.class, 0L,
				"Wielkość Pliku", null, null);
		fileTable.addContainerProperty("lastModificationDate", Date.class,
				null, "Data ostatniej modyfikacji", null, null);
		addComponent(fileTable);

	}

	/**
	 * Nadpisanie metody nie jest konieczne, ale dzieki temu jeżeli chcesz
	 * zobaczyć jak wygląda dodatek "ContextMenu" wystarczy, że uruchomisz
	 * mavena z profilem gwt.
	 */
	@Override
	public void attach() {
		super.attach();
		WebApplicationContext c = (WebApplicationContext) getApplication()
				.getContext();

		String useAddons = c.getHttpSession().getServletContext()
				.getInitParameter("addons");
		if (useAddons != null) {
			if (Boolean.parseBoolean(useAddons))
				addAddon();
		}
	}

	private void addAddon() {
		fileTable.setSelectable(true);
		fileTable.setImmediate(true);
		fileTable.addListener(new ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				if (ItemClickEvent.BUTTON_RIGHT == event.getButton()) {
					contextMenu.show(event.getClientX(), event.getClientY());
					contextMenu.setData(event.getItemId());

				}
			}
		});

		contextMenu = new ContextMenu();
		lookup = contextMenu.addItem("Podgląd");
		delete = contextMenu.addItem("Usuń");

		contextMenu.addListener(new ContextMenu.ClickListener() {
			public void contextItemClick(ContextMenu.ClickEvent event) {
				if (contextMenu.getData() != null) {
					Item item = fileTable.getItem(contextMenu.getData());
					File f = new File(TARGET + item.getItemProperty("name"));
					if (event.getClickedItem() == lookup) {
						try {
							FileReader fileReader = new FileReader(f);
							int c = -1;
							while ((c = fileReader.read()) != -1) {
								System.out.print((char) c);
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					if (event.getClickedItem() == delete) {
						f.delete();
						fileTable.removeItem(contextMenu.getData());
					}
				}
			}
		});

		addComponent(contextMenu);
	}

}
