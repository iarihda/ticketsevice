package com.iarihda.maven.ticketsevice;

import com.iarihda.maven.ticketsevice.model.Movie;
import com.iarihda.maven.ticketsevice.model.Screen;
import com.iarihda.maven.ticketsevice.model.Show;

/**
 * This is a setup which must be created before starting the booking process for a show. 
 * It takes care of creating the Screen, Movie and Show objects. 
 * All the constants such as the no. of rows and columns in a screen, the screen capacity, the screen size, the movie details, etc. are specified in here.
 * @author Adhirai Manickam
 */
public class TheatreSetup {
	
	final static int rows = 5;
	final static int columns = 5;
	final static int ticket_hold_time = 30000;
	
	private Screen scrn_1;
	private Movie mv_1;
	private Show shw_1;
	
	/**
	 * Constructor
	 */
	public TheatreSetup() {
		scrn_1 = new Screen("ScrnOne",rows*columns,50,true);
		mv_1 = new Movie("Avenger", "English", true);
		shw_1 = new Show(mv_1,scrn_1,"6PM",true);
	}

	/**
	 * @return no. of rows
	 */
	public static int getRowCount() {
		return rows;
	}
	
	/**
	 * @return no. of columns
	 */
	public static int getColumnCount() {
		return columns;
	}
	
	/**
	 * @return wait time before the hold expires
	 */
	public static int getHoldLimit(){
		return ticket_hold_time;
	}
	
	/**
	 * @return screen
	 */
	public Screen getScreen() {
		return scrn_1;
	}
	
	/**
	 * @return movie
	 */
	public Movie getMovie() {
		return mv_1;
	}
	
	/**
	 * @return show
	 */
	public Show getShow() {
		return shw_1;
	}
}
