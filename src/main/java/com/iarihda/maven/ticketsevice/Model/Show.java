package com.iarihda.maven.ticketsevice.Model;

/**
 * Setup entity for a show
 * @author Adhirai Manickam
 */
public class Show {
	
	private int id;
	private int movieId;
	private int screenId;
	private String time;
	private boolean isActive;
	
	/**
	 * Constructor
	 * @param showId - unique id of the show
	 * @param mId - id of the movie which is showing
	 * @param sId - id of the screen in which the movie is showing
	 * @param showTime - time of the show
	 * @param status - boolean value to indicate if the show is currently active or not
	 */
	Show(int showId, int mId, int sId, String showTime, boolean status){
		id = showId;
		movieId = mId;
		screenId = sId;
		time = showTime;
		isActive = status;
	}
	
	/**
	 * @return show id
	 */
	int getId() {
		return id;
	}
	
	int getMovieId() {
		return movieId;
	}
	
	int getScreenId() {
		return screenId;
	}
	
	String  getTime() {
		return time;
	}
	
	boolean isActive() {
		return isActive;
	}
	
	void setId(int showId) {
		id = showId;
	}
	
	void setMovieId(int mID) {
		movieId = mID;
	}
	
	void setScreenId(int sID) {
		screenId = sID;
	}
	
	void setTime(String showTime) {
		time = showTime;
	}
	
	void changeStatus() {
		isActive = !isActive;
	}
}
