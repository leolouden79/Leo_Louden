package com.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;

//This controller serves users the correct home page and fetches the appropriate list of reimbursements for them to see in their table.
public class HomeController {
	
	//Verify login credentials and send user to either Insured Home page or Policy Manager home page.
	public static void homePage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		

		if (req.getSession().getAttribute("LoggedInUser") != null) {

			User user = (User) req.getSession().getAttribute("LoggedInUser");

			//To prevent caching of home webpage so it cannot be accessed after logging out
			resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			resp.setHeader("Expires", "0"); // Proxies.

			if (user.getRole() == "Insured") {

				req.getRequestDispatcher("/WEB-INF/home.html").forward(req, resp);

			}

			else if(user.getRole() == "Policy Manager") {

				req.getRequestDispatcher("/WEB-INF/home2.html").forward(req, resp);

			}
		}

		else {
			req.getRequestDispatcher("badlogin.html").forward(req, resp);

		}
		
		
	}
	
	//Returns a json representation of logged-in user which includes the list of reimbursements applicable to that user.
	public static void getUserJson(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		

		HttpSession session  = req.getSession();
		
		if (session.getAttribute("LoggedInUser") == null) {

			resp.setContentType("text/plain");
			resp.getWriter().write(new ObjectMapper().writeValueAsString("Faliure"));
			return;
		}
		
		else {
			
		 	resp.setContentType("application/json");		
			resp.getWriter().write(new ObjectMapper().writeValueAsString(session.getAttribute("LoggedInUser")));
		}
		
			
		
	}
	
	
	
	
	

}
