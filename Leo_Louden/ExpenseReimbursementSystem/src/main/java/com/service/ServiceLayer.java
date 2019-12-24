package com.service;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.daolayer.DAO;
import com.model.Reimbursement;
import com.model.User;

public class ServiceLayer {
	
	final static Logger logger = Logger.getLogger(ServiceLayer.class);
	
	static String[] reimbStatus = {"offset", "Approved", "Denied", "Pending"};
	static String[] user_role   = {"offset", "Insured", "Policy Manager"};
	static String[]  reimbType = {"offset", "Rental Car", "Fuel Delivery", "Locksmith", "Car Tow"};
	
	static HashMap<Integer, String> users;
	
	public static void getUsers() {
		users = DAO.getUsers();
		users.put(0, " ");
	}
	
	public static User getUser(String username, String password) {
		
		
		//1. isUser real function
		//2. create user object
		//constructor that takes username, first and last name concancatenated, and auto generates new empty list of reimbursements 
		//3.  generate list of users and reimbursements
		//4. pass user to get list methods and then generate 3 reimbursement objects
		if(isUserReal(username, password)) {
			getUsers();
			User usr = DAO.createUser(username);
			logger.info("USER OBJECT = " + usr.toString());
			System.out.println("In is User real check win");
			
			createReimbursementList(usr);
			
			
			return usr;
		}
		
		
		System.out.println("FAIL");
		return null;	
	}



	public static Reimbursement[] createReimbursementList(User user) {
		
		//check if user is Insured or Policy Manager
		if(user.role == "Insured") {
			DAO.getUserReimbursements(user.reimbursements, user.id);
			
			for (Reimbursement burse: user.reimbursements) {
				//System.out.println("BUNNY");
				burse.setRemaingFields(users.get(burse.author), users.get(burse.resolver), reimbStatus[burse.status], reimbType[burse.type]);
				logger.info("reimbursements = " + burse.toString());
			}
		}
		
		else {
			
		}
		
		
		
		return null;
	}
	
	
	public static boolean isUserReal(String username, String password) {

		// check first if username exist by trying to get hashed password
		String databasePassowrd = DAO.getHashedPassword(username);
		//System.out.println("hash of password from database: " + databasePassowrd);
		
		if (databasePassowrd != null) {
			
			String newone = DAO.getHash(username, password);
			
			if (databasePassowrd.contentEquals(newone)) {
				
				return true;

			}

		}

		return false;
	}
	
	//create new reimbursement
	//create this function if you need to prepare the data before calling the DAO to insert the reimbursement. 
	//Otherwise, you could just call the DAO in the controller layer. Although that would break the design pattern.
	
	
	//update reimbursment status
	
	
	
	
	
	
	

	
	
	
}