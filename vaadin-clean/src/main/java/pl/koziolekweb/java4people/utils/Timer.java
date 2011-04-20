package pl.koziolekweb.java4people.utils;

import java.util.Date;

public class Timer {

	private Date start;
	private Date stop;

	public void start() {
		start = new Date();
	}

	public void stop() {
		stop = new Date();
	}

	public double getSeconds() {
		return ((double)stop.getTime() - (double)start.getTime()) / 1000;
	}
}
