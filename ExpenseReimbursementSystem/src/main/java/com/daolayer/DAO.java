package com.daolayer;

import com.model.Reimbursement;
import com.model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.log4j.Logger;

//This class's sole function is to perform database operations
public class DAO {
	
	final static Logger logger = Logger.getLogger(DAO.class);
	
	static {
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//Get Amazon RDS Credentials from Java class whose file name will be put in GitIgnore 
	private static String url = AmazonRDSCredentials.getUrl();
	private static String username = AmazonRDSCredentials.getUsername();
	private static String password = AmazonRDSCredentials.getPassword();

	private static String[] user_role = {"offset", "Insured", "Policy Manager"};
	
	//Get reimbursements that correspond to a single Insured user
	public static void getUserReimbursements(ArrayList<Reimbursement> reimbursements, String userid)	{
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimburse WHERE reimb_author = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  userid);
			ResultSet rs = ps.executeQuery();
			
			int eight = 0;
			while (rs.next()) 
			{
				try { eight = Integer.parseInt(rs.getString(8)); //This field can sometimes be null.
					
				} catch (NumberFormatException e) {
					eight = 0; //set the resolver id field to zero by default
					
				}
				
				reimbursements.add
				
					(new Reimbursement
							(
						
						Integer.parseInt(rs.getString(1)), 
						Integer.parseInt(rs.getString(2)), 
						rs.getString(3),
						getStringNullCheck(rs.getString(4)),
						getStringNullCheck(rs.getString(5)),
						Integer.parseInt(rs.getString(7)),
						eight,
						Integer.parseInt(rs.getString(9)),
						Integer.parseInt(rs.getString(10))
							)
					);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
			
	public static String getStringNullCheck(String rsString) {
		if (rsString == null) {
			return " ";
		}

		return rsString;
	}
	
	//Hash a username and password with a salt value that is stored in database
	public static String getHash(String new_username, String new_password) {
		
		String output = "";
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "{ call get_ers_users_hash_3(?,?,?) }";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.registerOutParameter(3, java.sql.Types.VARCHAR);
			cs.setString(1, new_username);
			cs.setString(2, new_password);
			cs.executeUpdate();
			
			output = cs.getString(3);
			return output;
			
		} catch (SQLException e) {
			System.out.println("In getHash");
			e.printStackTrace();
		}
		
		return output;
	}
	
	
	//Attempt to get a hashed password that corresponds with a given username
	public static String getHashedPassword(String new_username) {
	
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT ers_password  FROM ers_users WHERE ers_username = ?";
			

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, new_username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				return rs.getString(1);
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("In get hashed password exception");
			
		}

		return null;
		
	}
	
	//Create a user object by retrieving corresponding record from database
	public static User createUser(String new_username) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_users WHERE ers_username = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, new_username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) 
			{
				return
				
					new User(
						rs.getString(1), 
						new_username, 
						rs.getString(4) + " " + rs.getString(5),
						user_role[Integer.parseInt(rs.getString(7))]
							);
			}

		} catch (SQLException e) {
			System.out.println("in create user");
			e.printStackTrace();
		}
		
		return null;
	}
	
	//Create a Hash Map that links all user id's to a string with their first and last name.
	//This way, reimbursement objects will have strings of names instead of numerical id's to represent the resolver and author fields.
	public static HashMap<Integer, String> getUsers(){
		
		HashMap<Integer, String> users = new HashMap<Integer, String>();
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT ers_users_id, user_first_name, user_last_name  FROM ers_users";
		
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				 users.put(Integer.parseInt(rs.getString(1)), rs.getString(2) + " "+ rs.getString(3));
			
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}

		return users;
		
	}

	//Get all reimbursements in the system. This is used by Policy Managers only.
	public static void getAllReimbursements(ArrayList<Reimbursement> reimbursements) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimburse";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			int eight = 0;
			while (rs.next()) 
			{
				try { eight = Integer.parseInt(rs.getString(8)); //This field is null when whenever a ending reimbursement is created
					
				} catch (NumberFormatException e) {
					eight = 0; //set the resolver id field to zero by default
					
				}
				
				reimbursements.add
				
					(new Reimbursement
							(
						
						Integer.parseInt(rs.getString(1)), 
						Integer.parseInt(rs.getString(2)), 
						rs.getString(3),
						getStringNullCheck(rs.getString(4)),
						getStringNullCheck(rs.getString(5)),
						Integer.parseInt(rs.getString(7)),
						eight,
						Integer.parseInt(rs.getString(9)),
						Integer.parseInt(rs.getString(10))
							)
					);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
		
	
		
	//Insert new reimbursement ticket into database
	public static void insert_reimbursement(String amount, String submitted, String resolved, String description, String author, String resolver, String statusID, String typeId) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
	
			String sql = "INSERT INTO ers_reimburse VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setNull(1, 0); //Id is set by database sequence automatically when this param is Null
			ps.setInt(2, Integer.parseInt(amount));
			ps.setString(3, submitted);
			ps.setNull(4, 0); //Date resolved column
			ps.setString(5, description);
			ps.setNull(6, 0); //Receipt column
			ps.setInt(7, Integer.parseInt(author));
			ps.setNull(8, 0); //User ID of Resolver column
			ps.setInt(9, Integer.parseInt(statusID)); 
			ps.setInt(10, Integer.parseInt(typeId)); 
			ResultSet rs = ps.executeQuery();
			
			logger.info("New reimbursement Inserted into Database: [" + rs.toString() + "]");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}
	
	
	//Update a reimbursement to either approve or deny
	public static void updateReimbursement( String status, String resolved, String resolver, String reimburseId) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "UPDATE ers_reimburse SET reimb_status_id = ?, reimb_resolved = ?, reimb_resolver = ?  WHERE reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, resolved);
			ps.setString(3, resolver);
			ps.setString(4, reimburseId);
			
			ResultSet rs = ps.executeQuery();
			
			logger.info("Reimbursement Updated: [" + rs.toString() + "]");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	


}

