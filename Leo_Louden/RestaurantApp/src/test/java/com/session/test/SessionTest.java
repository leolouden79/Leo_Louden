package com.session.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.restaurant.Fridge;
import com.restaurant.Restaurant;
import com.restaurant.Session;
import com.restaurant.HealthInspector;


public class SessionTest {
	
	static Session sesh;
	
	@Test
	public void FridgeCreationNumberTest() {
		System.out.println("FridgeCreationNumberTest");


		String[] food1 = {"Fried Chicken", "Comb", "Cream of Mushroom Soup", null, null};
    	Fridge testFridge1 = new Fridge( food1, "Senor Poopies", 1,  null);
		Restaurant rest = new Restaurant("Wendy's", "1234", new Scanner(System.in), new ArrayList<HealthInspector> (), new ArrayList<Fridge>());
		
		rest.addFridge();
		assertEquals("This is the first Fridge Creation Number Test", 1, rest.getFridgeCreationNum());
		rest.addFridge();
		assertEquals("This is the second Fridge Creation Number Test", 2, rest.getFridgeCreationNum());
	
	
	}
	
	@Test
	public void fridgeFoodNumberTest() {
		
		String[] food1 = {"Fried Chicken", "Comb", "Cream of Mushroom Soup", null, null};
		Fridge testFridge = new Fridge( food1, "Senor Poopies", 1,  null);
		
		testFridge.addFood("Broccli");
		assertEquals("This is the First Fridge Add Food Test", 4, testFridge.getSize());
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	

}
