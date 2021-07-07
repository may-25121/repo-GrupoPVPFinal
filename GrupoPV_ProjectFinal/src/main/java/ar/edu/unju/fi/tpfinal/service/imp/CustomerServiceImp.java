package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.repository.ICustomerDAO;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;

@Service("customerServiceMysql")
public class CustomerServiceImp implements ICustomerService {

	@Autowired
	ICustomerDAO customerDAO;
	
	@Autowired
	private Customer customer;
	
	
	@Override
	public Customer getCustomer() {
		return this.customer;
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerDAO.save(customer);
	}

	@Override
	public Customer getCustomerById(int id) {
		Customer customer = customerDAO.findById(String.valueOf(id)).get();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customers = (List<Customer>) customerDAO.findAll();
		return customers;
	}

	@Override
	public void deleteCustomerById(int id) {
		customerDAO.deleteById(String.valueOf(id));
	}
/*
	@Override
	public List<Customer> getCustomers(String customerNumber, String city) {
		List<Customer> customers = new ArrayList<>();
		if(!customerNumber.isEmpty() && !customerDAO.findById(Integer.valueOf(customerNumber)).isEmpty()){
			customers.add(customerDAO.findById(Integer.valueOf(customerNumber)).get());
		}else if(!city.isEmpty() && !customerDAO.findByCity(city).isEmpty()) {
			customers = customerDAO.findByCity(city);
		}else {
			customers = (List<Customer>) customerDAO.findAll();
		}
		return customers;
	}
*/
	

	@Override
	public List<Customer> getCustomers(String var) {
		List<Customer> customers = new ArrayList<>();
		if(!var.isEmpty() && !customerDAO.findById(var).isEmpty()){
			customers.add(customerDAO.findById(var).get());
		}else if(!var.isEmpty() && !customerDAO.findByCity(var).isEmpty()) {
			customers = customerDAO.findByCity(var);
		}else {
			customers = (List<Customer>) customerDAO.findAll();
		}
		return customers;
	}

	
}
