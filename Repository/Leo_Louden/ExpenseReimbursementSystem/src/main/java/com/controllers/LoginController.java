package com.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.masterservlet.HelperData;
import com.model.User;
import com.service.ServiceLayer;

public class LoginController {
	
	public static HelperData login(HttpServletRequest req, HttpServletResponse resp) {
		HelperData responseData = new HelperData();
		HttpSession session  = req.getSession();
		User usr;
			
//		if((usr = (User) session.getAttribute("LoggedInUser")) != null)	{
//			System.out.println("In first if statement of login controller");
//			
//			responseData.uri = "http://localhost:9005/ExpenseReimbursementSystem/resources/html/home.html";
//			responseData.type = "redirect";
//			responseData.req = req;
//			responseData.resp = resp;
//			return responseData;
//			
//		}
		
		
			
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		usr = ServiceLayer.getUser(username, password);
		
		if(usr != null) {
			
			session.setAttribute("LoggedInUser", usr);
			
			
			responseData.uri = "http://localhost:9005/ExpenseReimbursementSystem/resources/html/home.html";
			responseData.type = "redirect";
			responseData.req = req;
			responseData.resp = resp;
			
			//returnResource =  "resources/html/home.html";
			
			//resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			//resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			//resp.setHeader("Expires", "0"); // Proxies.
			
			
			//test out object writer
			
			
			
			//resp.getWriter().write(new ObjectMapper().writeValueAsString(usr));
			
			
			
			
			
			
			
			//req.getRequestDispatcher("/resources/html/home.html").forward(req, resp);
		}
		
		else {

			responseData.uri = "http://localhost:9005/ExpenseReimbursementSystem/resources/html/badlogin.html";
			responseData.type = "redirect";
			responseData.req = req;
			responseData.resp = resp;
		}
		
		
		
		
		
		return responseData;
	}
	


}
