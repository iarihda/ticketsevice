package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Setup entity for a movie
 * @author Adhirai Manickam
 */
public class Movie {

	private int id;
	private String title;
	private String language;
	private boolean isActive;
	
	private static AtomicInteger idGenerator = new AtomicInteger(1000);
	
	/**
	 * Constructor
	 * @param movieId - unique identifier of the movie
	 * @param movieName - title of the movie
	 * @param movieLanguage - language of the movie 
	 * @param status - boolean value which indicates whether the movie is currently showing or not
	 */
	public Movie(String movieName, String movieLanguage, boolean status){
		id = idGenerator.getAndIncrement();
		title = movieName;
		language = movieLanguage;
		isActive = status;
	}
	
	/**
	 * @return movie id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return movie name
	 */
	String getTitle() {
		return title;
	}
	
	/**
	 * @return movie language
	 */
	String getLanguage() {
		return language;
	}
	
	/**
	 * @return current status of the movie (active or not)
	 */
	boolean isActive() {
		return isActive;
	}
	
	/**
	 * Used to change the movie name after the object is created.
	 * @param name
	 */
	void setTitle(String name) {
		title = name;
	}
	
	/**
	 * Used to change the language of the movie after the object is created.
	 * @param movieLanguage
	 */
	void setLanguage(String movieLanguage) {
		language = movieLanguage;
	}
	
	/**
	 * Change the status of the movie from active to inactive and vice versa
	 */
	void changeStatus() {
		isActive = !isActive;
	}
	
}
