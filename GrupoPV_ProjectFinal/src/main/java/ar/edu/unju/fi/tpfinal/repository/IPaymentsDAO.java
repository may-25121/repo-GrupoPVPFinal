package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.model.Payment;

public interface IPaymentsDAO extends CrudRepository<Payment, String>{
	
	public List<Payment> findByCustomerNumber(Customer customer);
	public List<Payment> findByCheckNumber(Payment payment);
	
}
