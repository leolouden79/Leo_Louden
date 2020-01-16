package com.controllers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import com.service.ServiceLayer;

//This Controller handles all request that deal with the creation and updating of reimbursements
public class ReimbursementController {
	
	
	//Create new reimbursement ticket based on form Submission by Insured User.
	public static void createReimbursementPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String responseString = "Faliure";
		HttpSession session = req.getSession();

		User user = ((User) (session.getAttribute("LoggedInUser")));
		
		AtomicInteger reimbOpCounter1 = (AtomicInteger) session.getAttribute("OperationCounter");

		//Ensure that user only creates one reimbursement ticket at a time, and that user is of type Insured
		if (reimbOpCounter1.get() == 0 && user.getRole() == "Insured") {

			reimbOpCounter1.set(1);
			ServiceLayer.createNewReimbursement(user, req, resp);
			responseString = "Success";
			reimbOpCounter1.set(0);

		}

		resp.setContentType("text/plain");
		resp.getWriter().write(new ObjectMapper().writeValueAsString(responseString));

	}

	//Approve or deny any pending reimbursement request
	public static void updateReimbursementPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String responseString = "Faliure";
		HttpSession session = req.getSession();

		AtomicInteger reimbOpCounter0 = (AtomicInteger) session.getAttribute("OperationCounter");

		User user = ((User) (session.getAttribute("LoggedInUser")));

		//Check if there is already a reimbursement operation being performed, and that user is of type Policy Manager
		if (reimbOpCounter0.get() == 0 && user.getRole() == "Policy Manager") {

			reimbOpCounter0.set(1);
			ServiceLayer.updateReimbursement(user, req, resp);
		    responseString = "Success";
			reimbOpCounter0.set(0);

		}

		resp.setContentType("text/plain");
		resp.getWriter().write(new ObjectMapper().writeValueAsString(responseString));

	}

}
