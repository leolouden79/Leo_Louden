package com.restaurant;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Session {
	
	final static Logger logger = Logger.getLogger(MainDriver.class);

	//not sure if I should have a constrcutor yet
	// changes made flag will be extra functionality.
	// getters and setters for these variables will also be extra functionality
	
	
	private ArrayList<Fridge> fridges;
	//Do we actually need this
	
	// instantiate for testing purposes
	//make a temporary constructor to load up some test data
	//load a list of usernames so that createLoginInfo can make sure it doesn't duplicates. 
	//I might make it extar functionality to ensure no duplicates
	private ArrayList<Restaurant> restaurants;
	private ArrayList<HealthInspector> healthInspectors;
	private Scanner sc;
	
	public Session() {
		Scanner sc = new Scanner(System.in);
		this.sc = sc;
	}
	

	public void restaurantEventLoop(Restaurant rest) {
		
		final int ADD_FRIDGE = 1;
		final int REMOVE_FRIDGE = 2;
		final int ADD_FOOD = 3;
		final int WITHDRAW_FOOD = 4;
		final int TRANSFER_FOOD = 5;
		final int GIVE_PERMISSION = 6;
		final int LOG_OUT = 7;

		System.out.println("Welcome " + rest.getUsername() + ". Choose an option by pressing a number and then pressing 'Enter'");
		System.out.println("");
		System.out.println("1) Add a Fridge");
		System.out.println("2) Remove a Fridge");
		System.out.println("3) Add Food to a Fridge");
		System.out.println("4) Take Food from a Fridge");
		System.out.println("5) Transfer Food Between Fridges");
		System.out.println("6) Give a Health Inspector Permission to Access Your Fridge");
		System.out.println("7) Log Out");
		System.out.println("");
		System.out.println("Enter here:");

		String line;
		int menuOption = 0;

		while (true) {
			line = sc.nextLine();
			try {
				menuOption = Integer.parseInt(line);
				if (menuOption > 0 && menuOption < 8) {
					break;

				}

			}

			catch (NumberFormatException e) {
				System.out.println("Enter Here:");
			}
		}
		
		switch (menuOption) {

		case ADD_FRIDGE:
			rest.addFridge();
			restaurantEventLoop(rest);
			break;

		case REMOVE_FRIDGE:
			rest.deleteFridge();
			restaurantEventLoop(rest);
			break;
			
		case ADD_FOOD:
			rest.addFood();
			restaurantEventLoop(rest);
			break;
			
		case WITHDRAW_FOOD:
			rest.withdrawFood();
			restaurantEventLoop(rest);
			break;

		case TRANSFER_FOOD:
			rest.transferFood();
			restaurantEventLoop(rest);
			break;

		case GIVE_PERMISSION:
			rest.giveHealthInspectorPermission();
			restaurantEventLoop(rest);
			break;
		
		case LOG_OUT:
		logout();
		break;
		
		}
		
				
	}

	public void healthInspectorEventLoop(HealthInspector inspec) {
		
		final int VIEW_FRIDGES = 1;
		final int REMOVE_FOOD = 2;
		final int LOG_OUT = 3;

		System.out.println("Welcome " + inspec.getUsername() + ". Choose an option by pressing a number and then pressing 'Enter'");
		System.out.println("");
		System.out.println("1) View Accessible Fridge");
		System.out.println("2) Remove Food From Fridge");
		System.out.println("3) Log Out");
		System.out.println("");
		System.out.println("Enter here:");

		String line;
		int menuOption = 0;

		while (true) {
			line = sc.nextLine();
			try {
				menuOption = Integer.parseInt(line);
				if (menuOption > 0 && menuOption < 4) {
					break;

				}

			}

			catch (NumberFormatException e) {
				System.out.println("Enter Here:");
			}
		}
		
		switch (menuOption) {

		case VIEW_FRIDGES:
			inspec.viewFridges();
			healthInspectorEventLoop(inspec);
			break;

		case REMOVE_FOOD:
			inspec.removeFood();
			healthInspectorEventLoop(inspec);
			break;

		case LOG_OUT:
			logout();
			break;	
		
		}

	}

	public void loadSession(String url, String username, String password) {
		//can either use this method or transport the logic to the health inspector constrcutor
		//should take into account if there are no restaurant to health inspector objects saved in the database.
		this.restaurants = new ArrayList<Restaurant> ();
		this.healthInspectors = new ArrayList<HealthInspector> ();
		
		//TEST DATA
//		String[] food = {"Spaghetti", "Pizza", "Cheese Burger", null, null};
//		ArrayList<HealthInspector> testInspecs = new ArrayList<HealthInspector> ();
//		Fridge testFridge = new Fridge( food, "Wendy's", 1, testInspecs);
//		
//		ArrayList<HealthInspector> testInspecs1 = new ArrayList<HealthInspector> ();
//		String[] food1 = {"Fried Chicken", "Comb", "Cream of Mushroom Soup", null, null};
//		Fridge testFridge1 = new Fridge( food1, "Senor Poopies", 1,  testInspecs1);
//		
//		
//		ArrayList<Fridge> restFridges = new ArrayList<Fridge>();
//		ArrayList<HealthInspector> testInspecs2 = new ArrayList<HealthInspector> ();
//		Restaurant rest = new Restaurant("Wendy's", "1234", sc, testInspecs2, restFridges);
//		
//		ArrayList<Fridge> inspecFridges = new ArrayList<Fridge>();
//		HealthInspector inspec = new HealthInspector("Bob Dole", "5678", inspecFridges, sc); 
		
		//Create restaurant objects first with empty list of fridges
		//Then create fridge objects with empty list of health inspectors
		//Then add fridges to restaurants
		//Then create Health inspector objects with empty list of accessible fridges
		//Then couple the fridges and health inspectors using a junction table 
		
//		rest.getFridges().add(testFridge);
//		rest.getFridges().add(testFridge1);
//		rest.getHealthInspectors().add(inspec);
		
		//Because these two are tighlty couple they need to be created together
//		testFridge1.addHealthInspector(inspec);
//		inspec.getAccessibleFridges().add(testFridge1);
		
		//Then create final list of restaurants and health inspectors
//		restaurants.add(rest);
//		healthInspectors.add(inspec);
		
		
		//TEST DATA
		
		ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
		Scanner sc = new Scanner(System.in);
		ArrayList<HealthInspector> inspecs = new ArrayList<HealthInspector> ();
		ArrayList<Fridge> fridges = new ArrayList<Fridge>();
		
		logger.info("Retrieveing Restaurant Credentials From Database");
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM restaurants";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				restaurants.add(
						new Restaurant(rs.getString(1),
								rs.getString(2), sc, inspecs, fridges
						)
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		
		
		
	}

	public void saveSession(String url, String username, String password) {
		//NEEDS TO MAKE SURE THAT EVERY FRIDGE HAS A UNIQUE FRIDGE CREATION NUMBER! Manually set
		
		logger.info("Insertering New Restaurant Credentials Into Database");
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "INSERT INTO restaurants VALUES(?,?)";

			for(Restaurant rest: restaurants) {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, rest.getUsername());
				ps.setString(2, rest.getPassword()); 
				
				try{
					ps.executeUpdate();
				}
				
				catch (SQLIntegrityConstraintViolationException e){
					
				}
			}
		

		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void startSession() {
		System.out.println("Welcome to the McSpankies Restaurant Manager App!");
		System.out.println("");
		System.out.println("Choose an option by pressing a number and then pressing 'Enter'");
		System.out.println("");
		System.out.println("1) Create Account");
		System.out.println("2) Log In");
		System.out.println("3) Exit");
		System.out.println("");
		System.out.println("Enter here:");

		String line;
		int optionNum = 0;

		while (true) {
			

			try {
				line = sc.nextLine();
				optionNum = Integer.parseInt(line);

				if (optionNum > 0 && optionNum < 4) { 
					break;
				} 
				
				else {
					System.out.println("Enter here:");
				}

			}

			catch (NumberFormatException e) {
				System.out.println("Enter here:");
			}

		}

		if (optionNum == 1) {
			createAccount();
		}

		else if (optionNum == 2) {
			this.logIn();
		}

		else if (optionNum == 3) {
			this.exit();
		}

	}

	public void createAccount() {
		System.out.println("Choose Account Type");
		System.out.println("1) Restaurant");
		System.out.println("2) Health Inspector");
		System.out.println("3) Back to Main Menu");
		System.out.println("");
		System.out.println("Enter here:");
		String line;
		int optionNum = 0;
		
		while (true) {

			try {
				line = sc.nextLine();
				optionNum = Integer.parseInt(line);

				if (optionNum > 0 && optionNum < 4) {
					break;
				} 
				
				else { 
					System.out.println("Enter here:");
				}

			}

			catch (NumberFormatException e) {
				System.out.println("Enter here:");
			}

		}
		
		//create test array of inspectors
		if (optionNum == 1) {
			String[] login = this.createLoginInfo();
			ArrayList<Fridge> restFridges = new ArrayList<Fridge> ();
			Restaurant rest = new Restaurant(login[0], login[1], sc, healthInspectors, restFridges);
			restaurants.add(rest);
			restaurantEventLoop(rest);
		}

		else if (optionNum == 2) {
			String[] login = this.createLoginInfo();
			ArrayList<Fridge>  inspecFridges = new ArrayList<Fridge> ();
			HealthInspector inspec = new HealthInspector(login[0], login[1], inspecFridges, sc);
			healthInspectors.add(inspec);
			healthInspectorEventLoop(inspec);
		}
		
		else if (optionNum == 3) {
			this.startSession();
		}

	}

	public String[] createLoginInfo() {
		String username = null;
		String password = null;
		String tempUsername;
		String tempPassword;
		System.out.println("Create A Username. Then press enter: ");
		

		// Username can be anything but an empty string. Can even be one space.
		while (true) {

			tempUsername = sc.nextLine();
			
			if (tempUsername.isEmpty()) {
				System.out.println("Choose A Username. Then Press Enter: ");
				System.out.println();
				
			}
			
			else if(usernameExist(tempUsername)) {
				System.out.println("Username Taken.");
				System.out.println("Choose A Username. Then Press Enter: ");
				System.out.println();
			}

			else {
				username = tempUsername;
				break;
			}

		}
		
		System.out.println("Choose a Password. Then press enter: ");
		// Password can be anything but empty string. Can even be one space.
		while (true) {

			tempPassword = sc.nextLine();

			if (tempPassword.isEmpty()) {
				System.out.println("Choose A Password. Then Press Enter: ");

			}

			else {
				password = tempPassword;
				break;
			}

		}

		String[] login = { username, password };
		//System.out.println("Username: " + login[0] + "Password: " + login[1]);
		return login;
	}

	private boolean usernameExist(String tempUsername) {
		
		for(Restaurant rest: restaurants) {
			//System.out.println("THIS IS A USERNAME: " + rest.getUsername());
			if(tempUsername.equals(rest.getUsername())) {
				return true;
			}
			
		}
		
		for(HealthInspector inspec: healthInspectors) {
			if(tempUsername.equals(inspec.getUsername())) {
				return true;
			}
			
		}

		return false;
	}

	public void logIn() {

		String tempUsername;
		String tempPassword;
		Object o;
		
		System.out.println("Enter Username. Then press enter: ");

		while (true) {

			tempUsername = sc.nextLine();
			
			if (tempUsername.isEmpty()) {
				System.out.println("Enter a Valid Username Here: ");
				System.out.println();
				
				
			}
			
			else if(usernameExist(tempUsername)) {
				break;
			}

			else {
				System.out.println("Username does not exist. Try Again");
				System.out.println();
				
			}

		}
		
		System.out.println("Enter Password Here: ");
		
		while (true) {

			tempPassword = sc.nextLine();
			
			if (tempPassword.isEmpty()) {
				System.out.println("Enter a Valid Password Here: ");
				
				
			}
			
			else if(	(o = passwordExist(tempUsername, tempPassword)) != null) {
					
				break;
			}

			else {
				System.out.println("Incorrect Password. Enter Here:");
	
			}

		}
		
		if (o instanceof Restaurant) {
			restaurantEventLoop((Restaurant) o);
		}
	
		else {
			healthInspectorEventLoop((HealthInspector) o);
			
		}
		
			
	}

	private Object passwordExist(String tempUsername, String tempPassword) {
		
		for(Restaurant rest: restaurants) {
			if(tempUsername.equals(rest.getUsername()) && tempPassword.equals(rest.getPassword())) {
				return rest;
			}
			
		}
		
		for(HealthInspector inspec: healthInspectors) {
			if(tempUsername.equals(inspec.getUsername()) && tempPassword.equals(inspec.getPassword())) {
				return inspec;
			}
			
		}
		
		return null;
	}

	public void exit() {
		System.out.println("Big McThankies From McSpankies for Using Our App!");
		//Maybe close all the scanners at the end?
		//save data

	}

	public void logout() {
		System.out.println("User Has Been Logged Out");
		System.out.println("");
		startSession();
	}

	public static void main(String[] args) {
		//This functionality will go in the main method in the Main Driver once the data base shit is set up.
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Getters, Setters and To String
	@Override
	public String toString() {
		return "Session [fridges=" + fridges + ", restaurants=" + restaurants + ", healthInspectors=" + healthInspectors
				+ "]";
	}

}
