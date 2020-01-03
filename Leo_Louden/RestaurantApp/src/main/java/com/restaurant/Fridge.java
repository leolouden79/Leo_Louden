package com.restaurant;
import java.util.ArrayList;
import java.util.Arrays;

public class Fridge {

	private String[] food;
	private String owner;
	private int fridgeNumber;
	private ArrayList<HealthInspector> approvedInspectors;
	private int size;
	
	public Fridge(String[] food, String owner, int fridgeNumber, ArrayList<HealthInspector> approvedInspectors) {
		super();
		//String array added to fridge needs to have size for 5 elements
		//the fridge constructor 
		this.food = food;
		this.owner = owner;
		this.fridgeNumber = fridgeNumber;
		this.approvedInspectors = approvedInspectors;
		this.size = 0;
		for(int i = 0; i < 5; i++) {
			if(food[i] != null) {
				this.size++;
			}
		}
	}
	
	public void addHealthInspector(HealthInspector inspec) {
		this.approvedInspectors.add(inspec);
	}

	public boolean addFood(String foodItem) {
		
		for(int index = 0; index < 5; index++) {
			if(food[index] == null ) {
				food[index] = foodItem;
				this.size++;
				return true;
				
			}
		}
		
		return false;
	}
	
	public void removeFood(int foodIndex) {
		 
		
		this.food[foodIndex] = null;
		this.size--;

		// If there is still one of more items in the array, reorganize it just to make
		// sure the deletion doesn't interfere with other functions
		if (this.size > 0) {
			reorganizeArray();
		}

		return;
			
		
	}	
	
	
	
	
//	@Override
//	public boolean equals(Object o) {
//		return false;
//	}
		
	public void reorganizeArray() {
		
		ArrayList<String> tempArrayList = new ArrayList<String>();
		
		for (int index = 0; index < 5; index++) {
			if(food[index] != null) {
				tempArrayList.add(food[index]);
			}
		}
		
		String [] newFood = new String[5];
		for(int kdex = 0; kdex < tempArrayList.size(); kdex++) {
			newFood[kdex] = tempArrayList.get(kdex);
			
		}
		
		food = newFood;
	}
	
	public int getSize() {
		return this.size;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

    //getters, setters and toString
	public int getFridgeNumber() {
		return fridgeNumber;
	}
	
	public void setFridgeNumber(int fridgeNumber) {
		this.fridgeNumber = fridgeNumber;
	}

	public String[] getFood() {
		return food;
	}
	
	public void setFood(String[] food) {
		this.food = food;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public ArrayList<HealthInspector> getApprovedInspectors() {
		return approvedInspectors;
	}
	
	public void setApprovedInspectors(ArrayList<HealthInspector> approvedInspectors) {
		this.approvedInspectors = approvedInspectors;
	}
	
	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Fridge ID: " + fridgeNumber +" Food = " + Arrays.toString(food) + ", Owner = " + this.owner;
	}
	
	
}







