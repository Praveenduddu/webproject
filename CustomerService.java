package de.zeroco.servlets;

import java.util.List;

import de.zeroco.util.Utility;

public class CustomerService {

	public static int getAge(String dateOfBirth) {
		return Utility.calculateTheAge(dateOfBirth);
	}
	
	public static CustomerPojo getObjectUsingParameters(int id, String name, String contact, String email, String date) {
		return new CustomerPojo(id, name, contact, email, date, getAge(date));
	}
	
	public static CustomerPojo getObjectUsingParameters(String name, String contact, String email, String date) {
		return new CustomerPojo(name, contact, email, date, getAge(date));
	}
	
	public static boolean isContactAndEmailExist(String contact, String email) {
		if (Utility.isBlank(CustomerDAO.get("contact", contact)) && Utility.isBlank(CustomerDAO.get("email", email))) {
			return false;
		}
		return true;
	}
	
	public static int getNumberOfRowsUpdated(CustomerPojo customer) {
		return CustomerDAO.update(customer);
	}
	
	public static int getNumberOfRowsDeleted(int id) {
		return CustomerDAO.delete(id);
	}
	
	public static List<CustomerPojo> getListOfObjectById(int id) {
		return CustomerDAO.get(id);
	}
	
	public static int getGeneratedKeyByObject(CustomerPojo customer) {
		return CustomerDAO.getGeneratedKey(customer);
	}
	
	public static List<CustomerPojo> getListOfObject() {
		return CustomerDAO.list();
	}
}
