package com.restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HealthInspector {
	
	private ArrayList<Fridge> accessibleFridges;
	private String username;
	private String password;
	private Scanner sc;
	
	//The restaurant will have the list of health inspectors. It will update the Health Inspectors accessible Fridges list. 
	public HealthInspector(String username, String password, ArrayList<Fridge> accessibleFridges, Scanner sc) {
		this.username = username;
		this.password = password;
		this.accessibleFridges = accessibleFridges;
		this.sc = sc;
		
	}
	
	public void viewFridges() {
		
		//for testing purposes DELETE. But put some of it in Session main method as test data
//		testAccessibleFridges.add(testFridge);
//		this.accessibleFridges = testAccessibleFridges;
		//for testing purposes DELETE But put some of it in Session main method as test data
	
		int index;
		Fridge f;
		for(index = 0; index < accessibleFridges.size(); index++) {
			f = accessibleFridges.get(index);
			System.out.println(index + ") Owner: " + f.getOwner() + " | Fridge Number: " + 
		f.getFridgeNumber() + " | Food: " + Arrays.toString(f.getFood()));
			System.out.println();
		}	
		
	}
	
	//method that restaurant calls
	public void grantPermissionForFridge(Fridge f) {
		accessibleFridges.add(f);
		
	}
	
	public void removeFood() {
		//should be an option to completely empty.
		
		//needs to make test for when there are no accessible fridges
		//needs to test for when the fridges are empty
		
		//first it display fridges
		//then is lets you pick a fridge
		//then it pick food from the fridge. There should be an option to pick one of the items, or all items, 
		
		//for testing purposes DELETE But put some of it in Session main method as test data
//		ArrayList<Fridge> testAccessibleFridges = new ArrayList<Fridge>();
//		testAccessibleFridges.add(testFridge);
//		this.accessibleFridges = testAccessibleFridges;
		//for testing purposes DELETE But put some of it in Session main method as test data
		
		System.out.println("Choose a Fridge to Remove Food From");
		System.out.println();
		int index;
		Fridge f;
		for(index = 0; index < accessibleFridges.size(); index++) {
			f = accessibleFridges.get(index);
			System.out.println(index + ") Owner: " + f.getOwner() + " | Fridge Number: " + 
		f.getFridgeNumber() + " | Food: " + Arrays.toString(f.getFood()));
			System.out.println();
		}
		
		//Code above will not be needed when program is set up.
		//But then again, you need the index number 
		
		//Select Fridge from list of accessible fridges.
		System.out.println("Enter here:");

		String line;
		int optionNum;
		int size = accessibleFridges.size();

		while (true) {
			
			try {
				line = sc.nextLine();
				optionNum = Integer.parseInt(line);

				if (optionNum > -1 && optionNum < size) { 
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
		
		Fridge selectedFridge = accessibleFridges.get(optionNum);
		String [] food = selectedFridge.getFood();
		
		System.out.println("Select a Food Item to Remove");
		
		//Select Food item from Fridge
		int kdex;
		String f2;
		for(kdex = 0; kdex < food.length; kdex++) {
			f2 = food[kdex];
			System.out.println(kdex + ") " + f2);
		}
		
		//Rename variable to fridgeOptionNum and foodOptionNum
		String line2;
		int optionNum2;
		int size2 = food.length;

		while (true) {
			
			try {
				line2 = sc.nextLine();
				optionNum2 = Integer.parseInt(line);

				if (optionNum2 > -1 && optionNum2 < size2) { 
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
		
		//create a temp string array array  to store all the food items that won't be removed.
		//Then set the selectedFridge's string array of food items using that list.
		ArrayList<String> newFood1 = new ArrayList<String>();
		String [] newFood2 = new String[5];
		for(String s2: food) {
			if(s2 != food[optionNum2]) {
				newFood1.add(s2);
			}
		}
		
		for(int jdex = 0; jdex < newFood1.size(); jdex++) {
			newFood2[jdex] = newFood1.get(jdex);
		}
		
		selectedFridge.setFood(newFood2);
		
		//We'll have to work around the fact that the list of food is a string array and not an array list of strings. Display null for empty indexes.
		System.out.println("NEW FOOD LIST: " + Arrays.toString(newFood2));
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	//getters, setters and toString
	public ArrayList<Fridge> getAccessibleFridges() {	 
		return this.accessibleFridges;
	}
	 
	public void setAccessibleFridges (ArrayList<Fridge> accessibleFridges) {	
		this.accessibleFridges = accessibleFridges;
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

	@Override
	public String toString() {
		return "HealthInspector [accessibleFridges=" + accessibleFridges + "]";
	}
	
	
	

}
