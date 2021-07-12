package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.model.PaymentsId;

public interface IPaymentsDAO extends CrudRepository<Payment, PaymentsId>{
	
//	public List<Payment> findByCustomerNumber(Customer customer);
//	public List<Payment> findByCheckNumber(Payment payment);
	
}
