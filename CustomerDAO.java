package de.zeroco.servlets;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import de.zeroco.jdbc.DBUtil;
import de.zeroco.util.Utility;

public class CustomerDAO {

	public static final String SCHEMA = "zerocode";
	public static final String TABLE_NAME = "customer";
	public static final String[] COLUMNS = {"name", "contact", "email", "date_of_birth", "age"};
	public static final String CONDITION_COLUMN = "pk_id";
	public static final String ASSIGN_OPERATOR = "=";
	
	public static int getGeneratedKey(CustomerPojo customer) {
		List<Object> values = getListFromObject(customer);
		int genereatedKey = 0;
		Connection connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, CustomerDAO.SCHEMA);
		genereatedKey = DBUtil.getGeneratedKey(SCHEMA, TABLE_NAME, Arrays.asList(COLUMNS), values, connection);
		Utility.closeConnection(connection);
		return genereatedKey;
	}
	
	public static List<CustomerPojo> list() {
		List<CustomerPojo> users = null;
		Connection connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
		users = getListOfObjectFromListOfMaps(DBUtil.list(SCHEMA, TABLE_NAME, Arrays.asList(), connection));
		Utility.closeConnection(connection);
		return users;
	}
	
	public static int update(CustomerPojo customer) {
		List<Object> values = getListFromObject(customer);
		int numOfRowsUpdate = 0;
		Connection connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
		numOfRowsUpdate = DBUtil.update(SCHEMA, TABLE_NAME, Arrays.asList(COLUMNS), values, CustomerDAO.CONDITION_COLUMN, customer.getId(), connection);
		Utility.closeConnection(connection);
		return numOfRowsUpdate;
	}
	
	public static int delete(int id) {
		int numOfRowsUpdate = 0;
		Connection connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
		numOfRowsUpdate = DBUtil.delete(SCHEMA, TABLE_NAME, CONDITION_COLUMN, id, connection);
		Utility.closeConnection(connection);
		return numOfRowsUpdate;
	}
	
	public static List<CustomerPojo> get(String column, Object value) {
		List<CustomerPojo> users = new ArrayList<>();
		Connection connection = Utility.getConnection(DBUtil.URL, DBUtil.USER, DBUtil.PASSWORD, SCHEMA);
		users = getListOfObjectFromListOfMaps(DBUtil.get(SCHEMA, TABLE_NAME, Arrays.asList(), column, ASSIGN_OPERATOR, value, connection));
		Utility.closeConnection(connection);
		return users;
	}
	
	public static List<Object> getListFromObject(CustomerPojo customer) {
		List<Object> values = new ArrayList<Object>();
		values.add(customer.getName());
		values.add(customer.getContact());
		values.add(customer.getEmail());
		values.add(customer.getDateOfBirth());
		values.add(customer.getAge());
		return values;
	}
	
	public static List<CustomerPojo> getListOfObjectFromListOfMaps(List<Map<String, Object>> list) {
		List<CustomerPojo> users = new ArrayList<>();
		for (Map<String, Object> map : list) {
			users.add(new CustomerPojo((int) map.get(CONDITION_COLUMN),(String) map.get(COLUMNS[0]),(String) map.get(COLUMNS[1]),(String) map.get(COLUMNS[2]),(String) map.get(COLUMNS[3]),(int) map.get(COLUMNS[4])));
		}
		return users;
	}
	
	public static List<CustomerPojo> get(int id) {
		return get(CONDITION_COLUMN, id);
	}
}
