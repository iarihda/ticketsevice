package com.iarihda.maven.ticketsevice;

import com.iarihda.maven.ticketsevice.model.Movie;
import com.iarihda.maven.ticketsevice.model.Screen;
import com.iarihda.maven.ticketsevice.model.Show;

public class TheatreSetup {
	
	final int rows = 5;
	final int columns = 5;
  	final int ticket_hold_time = 30000;
	
	private Screen scrn_1;
	private Movie mv_1;
	private Show shw_1;
	
	public TheatreSetup() {
		scrn_1 = new Screen("ScrnOne",rows*columns,50,true);
		mv_1 = new Movie("Avenger", "English", true);
		shw_1 = new Show(mv_1.getId(),scrn_1.getId(),"6PM",true);
	}
	
	public int getRowCount() {
		return rows;
	}
	
	public int getColumnCount() {
		return columns;
	}
	
	public int getHoldLimit(){
		return ticket_hold_time;
	}
	
	public Screen getScreen() {
		return scrn_1;
	}
	
	public Movie getMovie() {
		return mv_1;
	}
	
	public Show getShow() {
		return shw_1;
	}
}
