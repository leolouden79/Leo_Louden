package com.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.daolayer.DAO;
import com.model.Reimbursement;
import com.model.User;

//This class is the interface between the DAO class and the Controllers
public class ServiceLayer {
	
	final static Logger logger = Logger.getLogger(ServiceLayer.class);
	
	//Used to map the database id of the user object's fields into human readable strings
	private static String[] reimbStatus = {"offset", "Approved", "Denied", "Pending"};
	private static String[]  reimbType = {"offset", "Rental Car", "Fuel Delivery", "Locksmith", "Car Tow"};
	
	static HashMap<Integer, String> users;
	
	
	//Create a Hash Map of user ids linked to strings that represent the first and last name of that user
	public static void getUsers() {
		users = DAO.getUsers();
		users.put(0, " ");
	}
	
	
	
	//Construct a user object with its corresponding list of reimbursements
	public static User getUser(String username, String password) {
		
		if(isUserReal(username, password)) {
			getUsers();
			User usr = DAO.createUser(username);
			logger.info("USER OBJECT = " + usr.toString());
			
			createReimbursementList(usr);
			
			return usr;
		}
			
		return null;	
	}


	//Generate list of reimbursements depending on user role
	public static void createReimbursementList(User user) {
		
		
		if(user.getRole() == "Insured") {
			DAO.getUserReimbursements(user.getReimbursements(), user.getId());
			
			for (Reimbursement burse: user.getReimbursements()) {
			
				//Use the hash map of Users created in getUsers() and the string arrays of this class to set the remaining fields on the reimbursement object
				burse.setRemaingFields(users.get(burse.getAuthor()), users.get(burse.getResolver()), reimbStatus[burse.getStatus()], reimbType[burse.getType()]);
				logger.info("Reimbursements = " + burse.toString());
			}
		}
			
		
		else if (user.getRole() == "Policy Manager") {

			DAO.getAllReimbursements(user.getReimbursements());

			for (Reimbursement burse : user.getReimbursements()) {
				
				burse.setRemaingFields(users.get(burse.getAuthor()), users.get(burse.getResolver()), reimbStatus[burse.getStatus()],
						reimbType[burse.getType()]);

			}

		}
			
	}
	
	//Verify that user exists
	public static boolean isUserReal(String username, String password) {

		//Check if there is a hashed password that corresponds to inputed username
		String databasePassowrd = DAO.getHashedPassword(username);
		
		if (databasePassowrd != null) {
			
			//Hash the inputed password using a database function
			String newone = DAO.getHash(username, password);
			
			//Compare newly hashed password with hashed password that already exist in the database
			if (databasePassowrd.contentEquals(newone)) {
				
				return true;

			}

		}

		return false;
	}

	//Update Pending reimbursement to either approve or deny
	public static void updateReimbursement(User user, HttpServletRequest req, HttpServletResponse resp) {
		
		String id = req.getParameter("reimbId");
		String status = req.getParameter("reimbStatus");
		DateFormat dateFormat0 = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
		Date date0 = new Date();
		String resolved0 = dateFormat0.format(date0);
		String resolver0 = user.getId();
		
		DAO.updateReimbursement(status, resolved0, resolver0, id);
		
		//Refresh the logged in users list of reimbursements
		user.setReimbursements(new ArrayList<Reimbursement>());
		createReimbursementList(user);
				
	}

	//Create a new pending reimbursement ticket
	public static void createNewReimbursement(User user, HttpServletRequest req, HttpServletResponse resp) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy hh:mm:ss");
		Date date = new Date();
		
		String amount = req.getParameter("amount");
		String submitted = dateFormat.format(date);
		String resolved = "NULL";
		String description = req.getParameter("descrip");
		String author = user.getId();
		String resolver = "NULL";
		String statusID = "3";
		String typeId = req.getParameter("typeId");

		DAO.insert_reimbursement(amount, submitted, resolved, description, author, resolver, statusID, typeId);

		//Refresh the logged in users list of reimbursements
		user.setReimbursements(new ArrayList<Reimbursement>());
		ServiceLayer.createReimbursementList(user);
	}
		
	
}