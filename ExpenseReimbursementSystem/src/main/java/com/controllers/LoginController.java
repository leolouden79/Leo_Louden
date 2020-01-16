package com.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.service.ServiceLayer;

//This controller handles the logic for logging users in and out
public class LoginController {
	
	final static Logger logger = Logger.getLogger(LoginController.class);
	
	//Creates session for user if login credentials are valid
	public static void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session  = req.getSession();
		User user;
				
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		user = ServiceLayer.getUser(username, password);
		
		if(user != null) {
			

			logger.info(user.getRole() + "user: " + user.getName() + " lust logged in");
			
			session.setAttribute("LoggedInUser", user);
			
			//Only one reimbursement operation for a user at a time
			final AtomicInteger counter = new AtomicInteger(0);
			
			session.setAttribute("OperationCounter", counter);
			
			//Tell the client to request this page using the Post/Redirect/Get pattern	
			resp.sendRedirect("http://localhost:9005/ExpenseReimbursementSystem/resources/html/home.html");
		}
		
		else {

			resp.sendRedirect("http://localhost:9005/ExpenseReimbursementSystem/resources/html/badlogin.html");

		}
		
	}
	
	//Log user out by invalidating session
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws JsonProcessingException, IOException {
		
		HttpSession session  = req.getSession();
		session.invalidate();
	
		resp.setContentType("text/plain");
		resp.getWriter().write(new ObjectMapper().writeValueAsString("Success"));
		

	}
	


}
