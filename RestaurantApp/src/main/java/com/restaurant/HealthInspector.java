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
