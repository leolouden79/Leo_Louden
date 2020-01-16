package com.model;

import java.util.ArrayList;

//Object representation of a User
public class User {
	
	
	private String id;
	private String username;
	private String name;
	private String role;
	private ArrayList<Reimbursement> reimbursements;
	
	
	public User(String id, String username, String name, String role) {
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


	public String getId() {
		return id;
	}


	public String getUsername() {
		return username;
	}


	public String getName() {
		return name;
	}


	public String getRole() {
		return role;
	}


	public ArrayList<Reimbursement> getReimbursements() {
		return reimbursements;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public void setReimbursements(ArrayList<Reimbursement> reimbursements) {
		this.reimbursements = reimbursements;
	}


	
	


}
