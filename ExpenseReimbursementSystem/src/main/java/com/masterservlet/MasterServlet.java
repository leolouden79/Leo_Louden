package com.masterservlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



//The Master Servlet simply acts as an end point for all incoming request.
public class MasterServlet extends HttpServlet{
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestHelper.process(req, resp);
	
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestHelper.process(req, resp);
		
		
			
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
