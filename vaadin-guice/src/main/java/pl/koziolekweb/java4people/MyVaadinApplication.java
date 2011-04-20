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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.koziolekweb.java4people.data.User;
import pl.koziolekweb.java4people.ui.ApplicationWindow;
import pl.koziolekweb.java4people.ui.LoginWindow;

import com.google.inject.Inject;
import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application implements
		HttpServletRequestListener {

	@Inject
	private LoginWindow loginWindow;

	@Inject
	private ApplicationWindow applicationWindow;

	@Override
	public void init() {
		if (getUser() == null) {
			setMainWindow(loginWindow);
		} else {
			setMainWindow(applicationWindow);
			applicationWindow.init();
			setLogoutURL("/vaadin-guice/?logout");
		}
	}

	@Override
	public void close() {
		super.close();
		setUser(null);
	}

	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		
		if(request.getParameter("logout")!=null){
			Cookie cookie = new Cookie("username","");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
			close();
			return;
		}
		
		if (!isRunning()) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
				for (int i = 0; i < cookies.length; i++) {
					if ("username".equals(cookies[i].getName()))
						setUser(new User(cookies[i].getValue()));
				}
		} else {
			if (getUser() != null) {
				Cookie cookie = new Cookie("username",
						((User) getUser()).getLogin());
				cookie.setPath(getURL().getPath());
				cookie.setMaxAge(365 * 24 * 3600);
				response.addCookie(cookie);
			}
		}

	}

	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
	}
}
