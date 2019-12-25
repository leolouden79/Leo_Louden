package com.restaurant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.restaurant.Session;



public class MainDriver {
	final static Logger logger = Logger.getLogger(MainDriver.class);
	
	private static String url = "";
	private static String username = "";
	private static String password = "";

	
	
	
	public static void main(String[] args) {
		//databaseLoad();
		
		
		Session sesh = new Session();
		sesh.loadSession(url, username, password);
		sesh.startSession();
		sesh.saveSession(url, username, password);
		
		
		
	}


//	private static void databaseLoad() {
//		ArrayList<Restaurant> rests = new ArrayList<Restaurant>();
//		Scanner sc = new Scanner(System.in);
//		ArrayList<HealthInspector> inspecs = new ArrayList<HealthInspector> ();
//		ArrayList<Fridge> fridges = new ArrayList<Fridge>();
//		
//		
//		
//		try (Connection conn = DriverManager.getConnection(url, username, password)) {
//
//			String sql = "SELECT * FROM restaurants";
//
//			PreparedStatement ps = conn.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//
//			while (rs.next()) {
//				rests.add(
//						new Restaurant(rs.getString(1),
//								rs.getString(2), sc, inspecs, fridges
//						)
//				);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		for(Restaurant r: rests) {
//			System.out.println(r.getUsername() + " " + r.getPassword());
//		}
//		
//	}

}
