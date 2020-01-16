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
	
		Session sesh = new Session();
		sesh.loadSession(url, username, password);
		sesh.startSession();
		sesh.saveSession(url, username, password);
		
		
		
	}


}
