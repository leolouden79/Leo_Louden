package com.restaurant;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Restaurant {
	
	final static Logger logger = Logger.getLogger(Restaurant.class);
	
	private ArrayList<Fridge> fridges;
	private int fridgeCreationNum;
	private int fridgeCount;
	private ArrayList<HealthInspector> healthInspectors;
	
	private String username;
	private String password;
	private Scanner sc;
	
	public Restaurant(String username, String password, Scanner sc, ArrayList<HealthInspector> inspecs, ArrayList<Fridge> fridges1) {
		this.username = username;
		this.password = password;
		this.sc = sc;
		this.fridges = fridges1;
		this.fridgeCount = fridges1.size();
		this.healthInspectors = inspecs;
		this.fridgeCreationNum = 0;
	
	}
	
	
	//Uses fridgeCreationNum to identify fridges
	public void addFridge() {
		//Add fridge to array list and return fridge number that was created (why?)
		ArrayList<HealthInspector> inspecs = new ArrayList<HealthInspector> ();
		String[] food = new String[5];
		Fridge fridge = new Fridge(food, username, fridgeCreationNum, inspecs);
		fridges.add(fridge);
		fridgeCreationNum++;
		logger.info("FRIDGE CREATION NUMBER = " + fridgeCreationNum);
		fridgeCount++;
		System.out.println("");
		System.out.println("Fridge added to " + this.getUsername() + "'s Restaurant");
		System.out.println("");
		
	}
	
	//Only decreases fridge count
	//returns if you don't have more than one fridge
	//Removes fridge from Health Inspectors list of accessible fridges
	public void deleteFridge(){
		
		if(fridges == null || fridges.size() == 0) {
			System.out.println("You have no fridges.");
			return;
		}
		
		if(fridgeCount == 1) {
			System.out.println("You Cannot Delete Your Last Fridge");
			return;
		}
		
		//Choose a Fridge to delete
		System.out.println("Choose a Fridge to Remove");
		System.out.println();
		int index;
		Fridge fridge;
	
		for (index = 0; index < fridges.size(); index++) {
			fridge = fridges.get(index);
			System.out.println(index + ") "+ fridge.toString());
		}
		
		System.out.println();
		System.out.println("Enter here:");

		String line;
		int optionNum;

		while (true) {

			try {
				line = sc.nextLine();
				optionNum = Integer.parseInt(line);

				if (optionNum > -1 && optionNum < fridges.size()) {
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
		
		//Add a the ability to go back if you want
		//delete fridge from health inspector list
		fridge = fridges.get(optionNum);
		Fridge inspecFridge;
		ArrayList<Fridge> inspecFridges;
		
		ArrayList<HealthInspector> inspecs = fridge.getApprovedInspectors();
		for (HealthInspector inspec: inspecs) {
			inspecFridges = inspec.getAccessibleFridges();
			for(int i = 0; i < inspecFridges.size(); i++) {
				if(fridge == inspecFridges.get(i)) {
					inspecFridges.remove(fridge);
				}
			}
		}
		
		//Delete fridge from Restaurant's list
		fridges.remove(optionNum);
		System.out.println("Fridge Removed");
		System.out.println();
		fridgeCount--;
		
		
		
	}
	
	public void addFood() {
	
		if(fridges == null || fridges.size() == 0) {
			System.out.println("You have no fridges.");
			System.out.println();
			return;
		}
		
		System.out.println("Choose a Fridge to Add Food to");
		System.out.println();
		int index;
		Fridge fridge;
	
		for (index = 0; index < fridges.size(); index++) {
			fridge = fridges.get(index);
			System.out.println(index + ") "+ fridge.toString());
		}
		
		System.out.println();
		System.out.println("Enter here:");

		String line;
		int fridgeOptionNum;

		while (true) {

			try {
				line = sc.nextLine();
				fridgeOptionNum = Integer.parseInt(line);

				if (fridgeOptionNum > -1 && fridgeOptionNum < fridges.size()) {
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
		
		//Take in input for food
		System.out.println("Enter Food Item Here:");
		String line2;
		
		while (true) {

			line2 = sc.nextLine();

			if (line2.isEmpty()) {
				System.out.println("Enter Food Item Here: ");

			}

			else {
				
				break;
			}

		}
		
		
		Fridge selectedFridge = fridges.get(fridgeOptionNum);
		if(selectedFridge.addFood(line2) == true) {
			System.out.println(line2 + " Has Been Added to Fridge.");
			System.out.println();
			return;
		}
		
		else {
			System.out.println("Fridge is Full.");
			System.out.println();
		}
		
		
		
		
		
	}
	
	public void withdrawFood () {
		
		if(fridges == null || fridges.size() == 0) {
			System.out.println("You have no Fridges.");
			System.out.println();
			return;
		}
		
		//Make sure to check that the fridge has more one or more item. 
		System.out.println("Choose a Fridge to Withdraw Food From");
		System.out.println();
		int index;
		Fridge fridge;
	
		for (index = 0; index < fridges.size(); index++) {
			fridge = fridges.get(index);
			System.out.println(index + ") "+ fridge.toString());
		}
		
		System.out.println();
		System.out.println("Enter here:");

		String line;
		int fridgeOptionNum;

		while (true) {

			try {
				line = sc.nextLine();
				fridgeOptionNum = Integer.parseInt(line);

				if (fridgeOptionNum > -1 && fridgeOptionNum < fridges.size()) {
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
		
		//Check that selected fridge has one or more food items
		Fridge selectedFridge = fridges.get(fridgeOptionNum);
		if(selectedFridge.getSize() < 1) {
			System.out.println("Fridge has no Items to Delete");
			System.out.println();
			return;
		}
		
		
		//Choose Food Item from Menu
		System.out.println("Choose a Food Item to Remove");
		System.out.println();
		String[] foodItems = selectedFridge.getFood();
		
		for (int index2 = 0; index2 < selectedFridge.getSize(); index2++) {
			System.out.println(index2 + ") "+ foodItems[index2]);
		}
		
		System.out.println();
		System.out.println("Enter Food Item Here:");
		String line2;
		int foodOptionNum;

		while (true) {

			try {
				line2 = sc.nextLine();
				foodOptionNum = Integer.parseInt(line2);

				if (foodOptionNum > -1 && foodOptionNum < selectedFridge.getSize()) {
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
		
		selectedFridge.removeFood(foodOptionNum);
		System.out.println("Item Removed");
		System.out.println();
		
		
	}
	
	public void transferFood () {
		//takes input for food
		//checks if destination fridge is full and says, sorry fridge is full. 
		//Once it verifies that is has recieved to ints it will check the fridges to see if they meet the requirements for a transfer	
		System.out.println("Enter the Source Fridge Number");
		System.out.println();
		String line;
		int index;
		Fridge fridge;
		int sourceOptionNum;
		int destOptionNum;
	
		for (index = 0; index < fridges.size(); index++) {
			fridge = fridges.get(index);
			System.out.println(index + ") "+ fridge.toString());
		}
		
		System.out.println("Enter here:");
		
		// Get Source Option Number
		while (true) {

			try {
				line = sc.nextLine();
				sourceOptionNum = Integer.parseInt(line);

				if (sourceOptionNum > -1 && sourceOptionNum < fridges.size()) {
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
				//Get destination option number
				System.out.println("Enter the Destination Fridge Number: ");
				System.out.println();
				while (true) {

					try {
						line = sc.nextLine();
						destOptionNum = Integer.parseInt(line);

						if (destOptionNum > -1 && destOptionNum < fridges.size()) {
							
						
							if(destOptionNum == sourceOptionNum) {
								System.out.println("Error: Source and Destination Cannot be the Same.");
								System.out.println();
								return;
							}
							
							else if(fridges.get(sourceOptionNum).getSize() <= 0 ) {
								System.out.println("Error: Source has no items.");
								System.out.println();
								return;
							}
							
							else if(fridges.get(destOptionNum).getSize() >= 5 ) {
								System.out.println("Error: Destination is full.");
								System.out.println();
								return;
							}
							
							
							else {
								break;
							}
						}
							
						

						else {
							System.out.println("Enter here:");
						}

					}

					catch (NumberFormatException e) {
						System.out.println("Enter here:");
					}

				}
		
		
		//Choose a food item from the source fridge to transfer
		System.out.println("Choose a Food Item from the Source to Transfer");
		System.out.println();
		Fridge Source = fridges.get(sourceOptionNum); 
		Fridge Dest = fridges.get(destOptionNum);
		String[] foodItems = Source.getFood();
		
		for (int index2 = 0; index2 < Source.getSize(); index2++) {
			System.out.println(index2 + ") "+ foodItems[index2]);
		}
		
		System.out.println();
		System.out.println("Enter Food Item Here:");
		String line2;
		int foodOptionNum;

		while (true) {

			try {
				line2 = sc.nextLine();
				foodOptionNum = Integer.parseInt(line2);

				if (foodOptionNum > -1 && foodOptionNum < Source.getSize()) {
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
		
		Dest.addFood(foodItems[foodOptionNum]);
		Source.removeFood(foodOptionNum);
		System.out.println("Item Successfully Tranasfered!");
		System.out.println();
			
	}
	
	public void giveHealthInspectorPermission () {
		
		if(healthInspectors == null || healthInspectors.size() == 0) {
			System.out.println("There are no Health Inspectors Registered With Our System");
			System.out.println();
			return;
		}
		
		if(fridges == null || fridges.size() == 0) {
			System.out.println("You Have no Fridges For Health Inspectors to Examine");
			System.out.println();
			return;
		}
		
		//FOR TESTING PURPOSES DELETE WHEN DONE
		/*
		 * String[] testFood = {"Cheese Burger", "Bacon," }; String[] testFood1 =
		 * {"Spaghetti", "Donut", "Coffe" };
		 * 
		 * ArrayList<HealthInspector> testInspecs3 = new ArrayList<HealthInspector>();
		 * Fridge testFridge = new Fridge(testFood, "Portillos's", 2, testInspecs3);
		 * ArrayList<Fridge> testFridges = new ArrayList<Fridge>();
		 * 
		 * //This health inspector has testfridge added to it's list of accessible
		 * fridges HealthInspector testInspec = new HealthInspector("Jacob", "p4ssw0rd",
		 * testFridges, sc);
		 * 
		 * 
		 * //This test fridge has no health inspectors. It should be the only fridge to
		 * show up in the menu options ArrayList<HealthInspector> testInspecs2 = new
		 * ArrayList<HealthInspector>(); Fridge testFridge1 = new Fridge(testFood1,
		 * "Wendy's", 1, testInspecs2);
		 * 
		 * 
		 * //This fridge has testInspec added to it's list of approved Inspectors, so it
		 * should not show up in the menu option when choosing Jacob.
		 * testFridge.addHealthInspector(testInspec);
		 * 
		 * //For testing this.healthInspectors.add(testInspec);
		 * this.fridges.add(testFridge); this.fridges.add(testFridge1);
		 */
		//FOR TESTING PURPOSES DELETE WHEN DONE
		
		
		//Choose a Health InSpector
		System.out.println("Choose a Health Inspector to Give Access to");
		System.out.println();
		int index;
		HealthInspector inspec = null;

		for (index = 0; index < healthInspectors.size(); index++) {
			inspec = healthInspectors.get(index);
			System.out.println(index + ") Health Inspector Username: " + inspec.getUsername());

		}
		
		System.out.println();
		System.out.println("Enter here:");

		String line;
		int optionNum;

		while (true) {

			try {
				line = sc.nextLine();
				optionNum = Integer.parseInt(line);

				if (optionNum > -1 && optionNum < healthInspectors.size()) {
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
		
		//Create a list of fridges that the health inspector has not been given access to
		//Ensure that there are fridges the health inspector has not already been given access to. 
		ArrayList<Fridge> fridgeMenuOptions = new ArrayList<Fridge>();
		ArrayList<Fridge> inspecFridges = inspec.getAccessibleFridges();

		for (Fridge fr : fridges) {
			if (containsFridge(inspecFridges, fr) == false) {
				fridgeMenuOptions.add(fr);
			}
		}
		
		
		if (fridgeMenuOptions == null || fridgeMenuOptions.size() == 0) {
			System.out.println("There Are No Fridges that this Health Inspector Does Not Already Have Access To");
			return;
		}
			
				
		//Give them a list of fridges that the health inspector doesn't already have access to, rather than verifying if input has already been done.
		//Print to console "Fridges that {Health Inspector name} has not been granted access to"
		//If there are no fridges that the health inspector has not been acces to print "error "please create new fridge." and then take user to resaurant event loop main menu by just
		//"Select a fridge to grant access"
		//might need a function to leave and go back, but not necessary
		System.out.println("Grant " + inspec.getUsername() + " Access to one of the fridges below");
		System.out.println();
		int kdex;
		Fridge f3;
		
		
		for (kdex = 0; kdex < fridgeMenuOptions.size(); kdex++) {
			f3 = fridgeMenuOptions.get(kdex);
			System.out.println(kdex + ") " + f3.toString());
		}
		
		System.out.println();
		System.out.println("Enter here:");

		String line1;
		int fridgeOptionNum;

		while (true) {

			try {
				line1 = sc.nextLine();
				fridgeOptionNum = Integer.parseInt(line1);

				if (fridgeOptionNum > -1 && fridgeOptionNum < fridgeMenuOptions.size()) {
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
		
		Fridge selectedFridge = fridgeMenuOptions.get(fridgeOptionNum);
		selectedFridge.addHealthInspector(inspec);
		inspec.grantPermissionForFridge(selectedFridge);
		System.out.println("Permission Granted!");
		System.out.println();
		
	}
	
	
	public boolean containsFridge (ArrayList<Fridge> fridges, Fridge f) {
		
		for(Fridge fr: fridges) {
			if(fr == f) {
				return true;
			}
		}	
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//getters, setters and toString
	public ArrayList<Fridge> getFridges() {
		return fridges;
	}
	
	public void setFridges(ArrayList<Fridge> fridges) {
		this.fridges = fridges;
	}

	//change to fride creation number
	public int getFridgeCount() {
		return fridgeCount;
	}
	
	public void setFridgeCount(int count) {
		this.fridgeCount = count;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Scanner getSc() {
		return sc;
	}
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	public ArrayList<HealthInspector> getHealthInspectors() {
		return healthInspectors;
	}
	

	public void setHealthInspectors(ArrayList<HealthInspector> healthInspectors) {
		this.healthInspectors = healthInspectors;
	}
	

	public int getFridgeCreationNum() {
		return fridgeCreationNum;
	}

	public void setFridgeCreationNum(int fridgeCreationNum) {
		this.fridgeCreationNum = fridgeCreationNum;
	}

	@Override
	public String toString() {
		return null;
	}
	
	
}
