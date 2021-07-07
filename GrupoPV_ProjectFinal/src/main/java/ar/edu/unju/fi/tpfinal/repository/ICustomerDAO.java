package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Customer;

public interface ICustomerDAO extends CrudRepository<Customer, String> {

	public List<Customer> findByCity(String city);
	
}
