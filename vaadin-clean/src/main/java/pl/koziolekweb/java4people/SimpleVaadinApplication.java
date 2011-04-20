/*
 * Copyright 2009 IT Mill Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pl.koziolekweb.java4people;

import pl.koziolekweb.java4people.img.Picasso;
import pl.koziolekweb.java4people.tabs.BasicComponentsTab;
import pl.koziolekweb.java4people.tabs.CustomComponentsTab;
import pl.koziolekweb.java4people.tabs.FormComponentsTab;
import pl.koziolekweb.java4people.tabs.OtherComponentsTab;
import pl.koziolekweb.java4people.tabs.PerformanceTab;

import com.vaadin.Application;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

/**
 * Główna klasa aplikacji Vaadin.<br/>
 * Każda aplikacja Vaadin posiada klasę główną rozszerzającą klasę
 * {@link Application}.<br />
 * Zajrzyj do web.xml by zobaczyć jak wygląda konfiguracja servletu Vaadin.
 * 
 * @author koziolek
 */
@SuppressWarnings("serial")
public class SimpleVaadinApplication extends Application {

	/**
	 * Główne okno aplikacji. Odpowiada oknu przeglądarki. W domyślnej
	 * konfiguracji Vaadin nie obsługuje aplikacji otwieranych w wielu kartach
	 * przeglądarki.
	 */
	private Window window;

	/**
	 * Przykładowy layout oparty o zakładki.
	 */
	private TabSheet tabs;

	/**
	 * Metoda wywoływana przez framework w momencie uruchomienia aplikacji.
	 * Można ją traktować jak metodę {@link java.applet.Applet#init()}.Metoda
	 * nie przyjmuje parametrów, nie zwraca wartości, ani nie rzuca wyjątków
	 * weryfikowalnych.
	 */
	@Override
	public void init() {
		// tworzymy nowe okno
		window = new Window("java4people - V jak Vaadin");

		// layout
		tabs = new TabSheet();

		// dodajemy kolejne karty.
		tabs.addTab(new BasicComponentsTab(), "Podstawowe komponenty",
				new StreamResource(new Picasso('B'), "b.png", this));
		tabs.addTab(new FormComponentsTab(), "Formularze", new StreamResource(
				new Picasso('F'), "f.png", this));
		tabs.addTab(new OtherComponentsTab(), "Inne komponenty",
				new StreamResource(new Picasso('O'), "o.png", this));
		tabs.addTab(new CustomComponentsTab(), "Własne komponenty",
				new StreamResource(new Picasso('C'), "c.png", this));
		tabs.addTab(new PerformanceTab(), "Wydajność", new StreamResource(
				new Picasso('P'), "p.png", this));
		
		// następnie karty są dodawane do okna
		window.addComponent(tabs);

		// a okno do aplikacji.
		addWindow(window);
	}

}
