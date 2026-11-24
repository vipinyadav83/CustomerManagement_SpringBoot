package com.jtcindia.spring;
//public List<Customer>		getCustomerByEmail(String email);
//public List<Customer> 	getCustomerByCid(int cid);

import java.util.List;

public interface CustomerDAO {
	public void addCustomer(Customer cust);
	public List<Customer> getAllCustomers();
	public List<Customer> getCustomersByCity(String city); 
}
