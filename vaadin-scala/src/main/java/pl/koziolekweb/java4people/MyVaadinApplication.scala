package pl.koziolekweb.java4people

import com.vaadin.Application
import com.vaadin.ui._

class MyVaadinApplication extends Application{

 def init = {
	val mainWindow = new Window("hello")
	mainWindow.addComponent(new Label("hello scala!"))
	setMainWindow(mainWindow)
  }

}