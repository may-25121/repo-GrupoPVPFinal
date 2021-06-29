package ar.edu.unju.fi.tpfinal.service.imp;

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
		Customer customer = customerDAO.findById(id).get();
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		
		return null;
	}

	@Override
	public void deleteCustomerById(int id) {
		// TODO Auto-generated method stub
		
	}

}
