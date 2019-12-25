package com.daolayer;

import com.model.Reimbursement;
import com.model.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class DAO {
	
	static {
		
		System.out.println("In static block");
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static String url = "";
	private static String username = "";
	private static String password = "";
	private static String[] user_role = {"offset", "Insured", "Policy Manager"};
	
	//get user reimbursements
	public static void getUserReimbursements(ArrayList<Reimbursement> reimbursements, int userid)	{
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimburse WHERE reimb_author = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  Integer.toString(userid));
			ResultSet rs = ps.executeQuery();
			
			int eight = 0;
			while (rs.next()) 
			{
				try { eight = Integer.parseInt(rs.getString(8)); //because this field is sometimes null, make to sure check for this on javascript side
					
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
			//while(result.next());
			//return cs.getString(1);

		} catch (SQLException e) {
			System.out.println("In getHash");
			e.printStackTrace();
		}
		
		return output;
	}
	
	public static String getHashedPassword(String new_username) {
	
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT ers_password  FROM ers_users WHERE ers_username = ?";
		
			//String sql = "SELECT ers_password  FROM ers_users WHERE ers_username = " + new_username;
			
			System.out.println(sql);

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
						Integer.parseInt(rs.getString(1)), 
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
			System.out.println("getUsers");
			return users;
		}

		return users;
		
	}

	public static void getAllReimbursements(ArrayList<Reimbursement> reimbursements) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimburse";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			int eight = 0;
			while (rs.next()) 
			{
				try { eight = Integer.parseInt(rs.getString(8)); //because this field is sometimes null, make to sure check for this on javascript side
					
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
		
	
		
	
	public static void insert_reimbursement(String amount, String submitted, String resolved,
			String description, String author, String resolver, String statusID, String typeID) {

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

	
				
			String sql = "INSERT INTO ers_reimburse VALUES( NULL, ?, ?, ?, ?, NULL, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, amount);
			ps.setString(2, submitted);
			ps.setString(3, resolved);
			ps.setString(4, description);
			ps.setString(5, author);
			ps.setString(6, resolver);
			ps.setString(7, statusID);
			ps.setString(8, typeID);
			ResultSet rs = ps.executeQuery();
			
			
			
			
			
			
			//String sql = "INSERT INTO ers_status ers_reimbursement_status VALUES(" + test1 + "','" + test2 + "')"; 
			//String sql = "INSERT INTO ers_reimburse VALUES(" + "NULL," + amount + "','" + submitted + "','" + resolved + "','" + description + ",NULL," + author + "','" + resolver + "','" + statusID + "','" + typeID + "')";


			//Statement statement = conn.createStatement();
			//int numOfRowsChanged = statement.executeUpdate(sql);
			//System.out.println("The # of Inserted rows changed: " + numOfRowsChanged);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public static void updateReimbursement(String reimburseId, String decesion) {
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "UPDATE ers_reimburse SET reimb_status_id = ?  WHERE reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, decesion);
			ps.setString(2, reimburseId);
			ResultSet rs = ps.executeQuery();

			//Statement statement = conn.createStatement();
			//int numOfRowsChanged = statement.executeUpdate(sql);
			//System.out.println("The # of Updated rows changed: " + numOfRowsChanged);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

	
	
	
	

}

