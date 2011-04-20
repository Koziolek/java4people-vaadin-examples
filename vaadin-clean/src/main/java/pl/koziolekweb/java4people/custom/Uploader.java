package pl.koziolekweb.java4people.custom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * Klasa niestandardowego, złożonego komponentu do obsługi uploadowania plików
 * na serwer. Za pomocą standardowego komponentu {@link Upload} oraz kilku
 * interfejsów stworzyłem uniwersalny komponent pozwalający na ładowanie plików
 * na serwer.
 * 
 * @author koziolek
 * 
 */
@SuppressWarnings("serial")
public class Uploader extends CustomComponent implements Receiver,
		SucceededListener, FailedListener, ProgressListener {

	/**
	 * Korzeń drzewa elementów.
	 */
	private VerticalLayout root;

	/**
	 * Standardowy element do obsługi uploadu.
	 */
	private Upload upload;

	/**
	 * Przycisk pozwalajacy na zatrzymanie ładowania.
	 */
	private Button stopButton;

	/**
	 * Pasek postępu.
	 */
	private ProgressIndicator progressIndicator;

	/**
	 * panel na którym umieszczam uploader i przycisk do zatrzymywania.
	 */
	private HorizontalLayout uploadPanel;

	/**
	 * Katalog w którym będą składowane pliki.
	 */
	private String targetDirectory;

	/**
	 * Konstruktor wymaga ustawienia docelowego katalogu dla uploadowanych
	 * plików.
	 * 
	 * @param targetDirectory
	 */
	public Uploader(String targetDirectory) {
		root = new VerticalLayout();
		setCompositionRoot(root);
		this.targetDirectory = targetDirectory;
		buildLayout();
	}

	/**
	 * Metoda pozwala na dodanie elementu do komponentu.
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
	 * Metoda pozwala na dodanie dodatkowych listenerów nasłuchujących zdarzeń
	 * zwiazanych z niepowodzeniem operacji.
	 * 
	 * @param listener
	 *            dodawany listener.
	 */
	public void addListener(FailedListener listener) {
		upload.addListener(listener);
	}

	/**
	 * Metoda pozwalająca na dodanie dodatkowych listenerów nasłuchujących
	 * zdarzeń zwiazanych z pomyśłnym zakończeniem operacji uploadu.
	 * 
	 * @param listener
	 *            dodawany listener.
	 */
	public void addListener(SucceededListener listener) {
		upload.addListener(listener);
	}

	/**
	 * Ta metoda konfiguruje strumień wyjściowy do którego będzie zapisany plik.
	 * 
	 * @param filename
	 *            nazwa oryginalnego pliku.
	 * @param mimeType
	 *            typ mime oryginalnego pliku.
	 */
	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null;
		File outFile = new File(targetDirectory + "/" + filename);
		try {
			fos = new FileOutputStream(outFile);
			progressIndicator = new ProgressIndicator();
			progressIndicator.setPollingInterval(100);
			root.addComponent(progressIndicator);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fos;
	}

	/**
	 * 
	 * Metoda pozwala na usunięcie elementu z komponentu.
	 * 
	 * @param c
	 *            element do usunięcia.
	 * 
	 */
	@Override
	public void removeComponent(Component c) {
		root.removeComponent(c);
	}

	/**
	 * Metoda wywoływana cyklicznie w momencie gdy operacja uploadu jest
	 * wykonywana.
	 * 
	 * @param readBytes
	 *            ilosć załadowanych danych.
	 * @param contentLength
	 *            całkowita ilość danych do załadowania.
	 * 
	 */
	public void updateProgress(long readBytes, long contentLength) {
		progressIndicator.setValue(new Float((double) readBytes
				/ (double) contentLength));
	}

	/**
	 * Metoda wywoływana w momencie gdy operacja zakończy się niepowodzeniem.
	 * 
	 * @param event
	 *            zdarzenie zawiera informacje o przyczynach.
	 */
	public void uploadFailed(FailedEvent event) {
		removeComponent(progressIndicator);
		addComponent(new Label("SOA #1 "
				+ event.getReason().getLocalizedMessage()));
	}

	/**
	 * Metoda wywoływana w momencie gdy operacja zakończy się sukcesem.
	 * 
	 * @param event
	 *            zdarzenie zawiera informacje o załadowanym pliku.
	 */
	public void uploadSucceeded(SucceededEvent event) {
		removeComponent(progressIndicator);
		addComponent(new Label("Pomyślnie załadowano: " + event.getFilename()));
	}

	/**
	 * Metoda tworząca layout.
	 */
	private void buildLayout() {
		uploadPanel = new HorizontalLayout();

		upload = new Upload("Wybierz plik do załadowania", this);
		upload.setButtonCaption("Załaduj");
		upload.addListener((SucceededListener) this);
		upload.addListener((FailedListener) this);
		upload.addListener((ProgressListener) this);
		stopButton = new Button("Zatrzymaj");
		stopButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				upload.interruptUpload();
			}
		});
		uploadPanel.addComponent(upload);
		uploadPanel.addComponent(stopButton);

		addComponent(uploadPanel);
	}
}
