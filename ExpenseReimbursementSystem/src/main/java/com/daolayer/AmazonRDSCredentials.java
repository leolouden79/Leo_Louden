package com.daolayer;

public class AmazonRDSCredentials {
	
	

	private static String url = "jdbc:oracle:thin:@revychan.c3aa4wolhtt6.us-east-2.rds.amazonaws.com:1521:orcl";
	private static String username = "leo";
	private static String password = "p4ssw0rd";
	
	static String getUrl() {
		return url;
	}
	static String getUsername() {
		return username;
	}
	static String getPassword() {
		return password;
	}
	

}
