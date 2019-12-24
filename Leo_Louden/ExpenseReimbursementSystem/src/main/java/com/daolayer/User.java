package com.daolayer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Reimbursement;



public class User {
	
	//we need the user id to create new request 
	
		public int id;
		public String username;
		public String name;
		public String role;
		public ArrayList<Reimbursement> reimbursements;
		
		
		public User(int id, String username, String name, String role) {
			this.id = id;
			this.username = username;
			this.name = name;
			this.role = role;
			this.reimbursements = new ArrayList<Reimbursement>();
		}

		
		
		
		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", name=" + name + ", role=" + role + "]";
		}
	

}
