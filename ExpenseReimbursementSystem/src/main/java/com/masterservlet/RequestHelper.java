package com.masterservlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controllers.LoginController;
import com.daolayer.DAO;
import com.model.User;



public class RequestHelper {
	
	public static HelperData process(HttpServletRequest req, HttpServletResponse resp) {
		
		HelperData responseData = new HelperData();
		HttpSession session  = req.getSession();
		//String responseUri;
		//String responseType;
			
		
		switch(req.getRequestURI()) {
		
		case "/ExpenseReimbursementSystem/masterserv": 
			
			responseData = LoginController.login(req,resp);		
			return responseData;
			
			
			//approval and denials //parames will be reimbursement id and approval or denial
		case "/ExpenseReimbursementSystem/verify":
			String id = req.getParameter("reimburseId");
			String dec = req.getParameter("decesion");
			DAO.updateReimbursement(id, dec);
			return null;
			
			// creating new reimbursements
		case "/ExpenseReimbursementSystem/newre":
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			String amount = req.getParameter("amount");
			String submitted = req.getParameter(dateFormat.format(date));
			String resolved = "NULL";
			String description = req.getParameter("descrip");
			String author = ((User)(session.getAttribute("LoggedInUser"))).id;
			String resolver = "NULL";
			String statusID = req.getParameter("statusID");
			String typeID = req.getParameter("typeID");

			DAO.insert_reimbursement(amount, submitted, resolved, description, author, resolver, statusID, typeID);
			System.out.println(dateFormat.format(date));
			return null;

			
			// logout post
		case "/ExpenseReimbursementSystem/logout":
			return null;

			// json request for users
		case "/ExpenseReimbursementSystem/jason":
			
			if(session.getAttribute("LoggedInUser") == null) {
				System.out.println("You done goofed");
			};
			//resp.getSession
			responseData.type = "json";
			return responseData;
			
				
		default:
			System.out.println("Default requestHelper Fail");
			System.out.println(req.getRequestURI());
			return null;
		
		}
					
	}

}
