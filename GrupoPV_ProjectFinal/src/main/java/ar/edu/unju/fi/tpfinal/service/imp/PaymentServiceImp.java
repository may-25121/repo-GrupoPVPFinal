package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.model.PaymentsId;
import ar.edu.unju.fi.tpfinal.repository.ICustomerDAO;
import ar.edu.unju.fi.tpfinal.repository.IPaymentsDAO;
import ar.edu.unju.fi.tpfinal.service.IPaymentService;

@Service("paymentServiceMysql")
public class PaymentServiceImp implements IPaymentService {
	
	@Autowired
	IPaymentsDAO paymentDAO;
	
	@Autowired
	ICustomerDAO customerDAO;
	
	@Autowired
	private Payment payment;
	
	@Override
	public Payment getPayment() {
		return this.payment;
	}

	@Override
	public void savePayment(Payment payment) {
		paymentDAO.save(payment);

	}

	@Override
	public Payment getPaymentById(PaymentsId id) {
		Payment payment = paymentDAO.findById(id).get();
		return payment;
	}

	@Override
	public List<Payment> getAllPayment() {
		List<Payment> payment = (List<Payment>) paymentDAO.findAll();
		return payment;
	}

	@Override
	public void deletePaymentById(PaymentsId id) {
		paymentDAO.deleteById(id);
	}

	@Override
	public boolean getCheckPaymentById(PaymentsId id) {
		boolean var=false;
		if(id!=null && !paymentDAO.findById(id).isEmpty()) {
			var = true;
		}
		return var;
	}
	
	//Falta el metodo de busqueda
/*
	@Override
	public List<Payment> getPayments(String var) {
		List<Payment> payment = new ArrayList<>();
		Customer customer = new Customer();
		customer = customerDAO.findById(var).get();
		Payment payments = new Payment();
		payments = paymentDAO.findById(var).get();
		if(!var.isEmpty() && !paymentDAO.findByCustomerNumber(customer).isEmpty()) {
			payment = paymentDAO.findByCustomerNumber(customer);
		}else if(!var.isEmpty() && !paymentDAO.findByCheckNumber(payments).isEmpty()) {
			payment = paymentDAO.findByCheckNumber(payments);
		}else {
			payment = (List<Payment>) paymentDAO.findAll();
			}
		return payment;
	}
*/
}
