package com.iarihda.maven.ticketsevice.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Setup entity for a show
 * @author Adhirai Manickam
 */
public class Show {
	
	private int id;
	private Movie movie;
	private Screen screen;
	private String time;
	private boolean isActive;
	
	private static AtomicInteger idGenerator = new AtomicInteger(1000);
	
	/**
	 * Constructor
	 * @param mv_1 - id of the movie which is showing
	 * @param scrn_1 - id of the screen in which the movie is showing
	 * @param showTime - time of the show
	 * @param status - boolean value to indicate if the show is currently active or not
	 */
	public Show(Movie mv_1, Screen scrn_1, String showTime, boolean status){
		validateParams(mv_1,scrn_1,showTime);
		id = idGenerator.getAndIncrement();
		movie = mv_1;
		screen = scrn_1;
		time = showTime;
		isActive = status;
	}
	
	private void validateParams(Movie mv_1, Screen scrn_1, String showTime) {
		if(mv_1==null || scrn_1==null) {
			throw new IllegalArgumentException("Both movie and screen are required to create show.");
		}
		if(!mv_1.isActive()) {
			throw new IllegalArgumentException("Movie is not active. Cannot create show.");
		}
		if(!scrn_1.isActive()) {
			throw new IllegalArgumentException("Screen is not active. Cannot create show.");
		}
		if(showTime==null)
			throw new IllegalArgumentException("Show time cannot be null");
	}

	/**
	 * @return show id
	 */
	public int getId() {
		return id;
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public String  getTime() {
		return time;
	}
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setMovie(Movie m) {
		validateParams(m, screen, time);
		movie = m;
	}
	
	public void setScreen(Screen s) {
		validateParams(movie, s, time);
		screen = s;
	}
	
	public void setTime(String showTime) {
		validateParams(movie, screen, showTime);
		time = showTime;
	}
	
	public void changeStatus() {
		isActive = !isActive;
	}
}