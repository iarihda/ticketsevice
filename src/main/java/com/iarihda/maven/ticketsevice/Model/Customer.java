package com.iarihda.maven.ticketsevice.Model;

/**
 * Transaction entity for the customers
 * @author Adhirai Manickam
 */
public class Customer {
	int id;
	String email;
	
	/**
	 * Constructor
	 * @param customerId - unique id
	 * @param emailAddress - email id of the customer
	 */
	Customer(int customerId, String emailAddress) {
		id = customerId;
		email = emailAddress;
	}
	
	int getId() {
		return id;
	}
	
	String getEmail() {
		return email;
	}
	
	void setId(int customerId) {
		id = customerId;
	}
	
	void setEmail(String emailAddress) {
		email = emailAddress;
	}
}
