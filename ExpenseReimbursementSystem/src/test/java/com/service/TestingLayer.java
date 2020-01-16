package com.service;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;

import com.daolayer.AmazonRDSCredentials;
import com.daolayer.DAO;

public class TestingLayer {

	//Test that getHash does not return null
	@Test public void SessionsHashTest() {
		
		assertThat(DAO.getHash("username", "password"), notNullValue());
		
	}
	
	//Testing if hashing function returns right value
	@Test public void HashingFunctionTest() {
		
		String result_1 = DAO.getHash("JayMoney", "supercool");
		assertEquals(result_1, "F87525C5646971DF8FCE936EBA8347FB");
		
		String result_2 = DAO.getHash("BobAdmin", "dolemite");
		assertEquals(result_2, "F1E626A4EDFE2D271C0302B9B946B562");
		
	}
	
	//Testing how to create prepared statements
	@Test public void PreparedStatement() {

		String url = AmazonRDSCredentials.getUrl();
		String username = AmazonRDSCredentials.getUsername();
		String password = AmazonRDSCredentials.getPassword();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT reimb_type FROM ers_reimbursement_type WHERE reimb_type_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, 5);
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				
				assertEquals(rs.getString(1), "Test");
			
			}	

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	//Test that the DAO layer is constructing the users Hash Map correctly 
	@Test public void getUsers() {
		 HashMap<Integer, String> users = new HashMap<Integer, String> () ;
		 users.put(1, "Jay Byrd");
		 users.put(2, "Bod Dole");
		 users.put(3, "Jeremy Meeks");
		 users.put(4, "Regina Smith");
		 
		 HashMap<Integer, String> users2 = DAO.getUsers();
		 
		 assertEquals(users.get(1), users2.get(1));
		 assertEquals(users.get(2), users2.get(2));
		 assertEquals(users.get(3), users2.get(3));
		 assertEquals(users.get(4), users2.get(4));
		
	}
	
	

}
