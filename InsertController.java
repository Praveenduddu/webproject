package de.zeroco.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InsertController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String contact = request.getParameter("contact");
		String dateOfBirth = request.getParameter("date");
		try {
			if (!CustomerService.isContactAndEmailExist(contact, email)) {
				CustomerPojo customer = CustomerService.getObjectUsingParameters(name, contact, email, dateOfBirth);
				response.getWriter().print("Hi " + name + " this is your ID :" + CustomerService.getGeneratedKeyByObject(customer));
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/CustomerDetails.html");
				dispatcher.include(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
