package com.masterservlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.service.ServiceLayer;

import com.masterservlet.HelperData;

public class MasterServlet extends HttpServlet{
	
	
	//During forwarding, the server goes back to the web.xml file to find the correct mapping. Be wary of creating infinite loops.
	
	//If you try to forward a response with a slash in front of the argument, 
	//it will append the forward string to the root of the application and try to find the resource from there (Most of the time I've tried it).
			
	//If you remove the slash, it will try to find the resource from within the current directory that was pointed to by the request url.
	//In other words, it will append the forward string to the request's url.
			
	//If you don't create mapping for a page, then it will always be possible to type in its absolute path directly and fetch it. 
	//So if you want to prevents users from accessing a webpage by just typing it's name in, you have to create a mapping in the web.xml
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("In Do Get");
		
		//get argument back from request helper that determines whether to do forward or redirect and have an if statement in both the post and 
		//do get methods for both options
		
		//the login and should probabaly both do  does session validation
		//on the front end, the cookie is automatically sent with the request (if it was set by the ).
		
		
//		HelperData respsonseData = RequestHelper.process(req, resp);
//		
//		if(respsonseData.type == "redirect") {
//			resp.sendRedirect(respsonseData.uri);
//		}
		
		
		
		
		if(req.getSession().getAttribute("LoggedInUser") != null) {
			
			User use = (User) req.getSession().getAttribute("LoggedInUser");
				
			if (use.role == "Insured"){
				req.getRequestDispatcher("/WEB-INF/home.html").forward(req, resp);
			}
			
			else {
				req.getRequestDispatcher("/WEB-INF/home2.html").forward(req, resp);
			}
		}
		
		else {
			req.getRequestDispatcher("badlogin.html").forward(req, resp);
				
		}
		
		
		//second if statements
		
		//json statement
		
		
		
		//req.getRequestDispatcher("home2.html").forward(req, resp);
		//resp.sendRedirect("badlogin.html");
			
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST! " + req.getRequestURI());
		
		
		HelperData respsonseData = RequestHelper.process(req, resp);
		
		if(respsonseData.type == "redirect") {
			resp.sendRedirect(respsonseData.uri);
		}
		
		//change this to a get request
		if(respsonseData.type == "json") {
			
			HttpSession session  = req.getSession();
			
			resp.getWriter().write(new ObjectMapper().writeValueAsString(session.getAttribute("LoggedInUser")));
		}
		
		
		//can forward to an html page or another servlet, or an image, or some other resource. Maybe even an mp3.
		
		//resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		//resp.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		//resp.setHeader("Expires", "0"); // Proxies.
		//req.getRequestDispatcher(returnResource).forward(req, resp);
		
		
		//resp.sendRedirect("resources/html/home.html");
		
		
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
