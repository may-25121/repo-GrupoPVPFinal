package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Customer;

public interface ICustomerService {
	
	public Customer getCustomer();
	public void saveCustomer(Customer customer);
	public Customer getCustomerById(int id);
	public List<Customer> getAllCustomers();
	public void deleteCustomerById(int id);
	public List<Customer> getCustomers(String customerNumber, String city);
	

}
