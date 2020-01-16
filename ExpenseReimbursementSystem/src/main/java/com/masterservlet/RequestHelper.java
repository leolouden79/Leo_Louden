package com.masterservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.controllers.HomeController;
import com.controllers.LoginController;
import com.controllers.ReimbursementController;
import com.fasterxml.jackson.databind.ObjectMapper;


//The request helper chooses a controller to handle the request based on the resource requested.
public class RequestHelper {
	
	
	public static void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		switch (req.getRequestURI()) {

		//Login
		case "/ExpenseReimbursementSystem/masterserv":
			LoginController.login(req, resp);
			return;
			
		//Home Page
		case "/ExpenseReimbursementSystem/resources/html/home.html":
			HomeController.homePage(req, resp);
			return;
			
		//Aprove or Deny reimbursement
		case "/ExpenseReimbursementSystem/update":
			ReimbursementController.updateReimbursementPage(req, resp);
			return;

		//Create new reimbursement request
		case "/ExpenseReimbursementSystem/newre":
			ReimbursementController.createReimbursementPage(req, resp);
			return;

		//Logout
		case "/ExpenseReimbursementSystem/logout":	
			LoginController.logout(req, resp);
			return;

		//Json request for User object and corresponding list of reimbursements
		case "/ExpenseReimbursementSystem/json":		
			HomeController.getUserJson(req, resp);
			return;

		default:
			resp.setContentType("text/plain");
			resp.getWriter().write(new ObjectMapper().writeValueAsString("Faliure"));
			return;

		}

	}

}
