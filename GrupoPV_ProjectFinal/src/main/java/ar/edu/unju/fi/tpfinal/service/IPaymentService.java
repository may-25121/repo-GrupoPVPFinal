package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.model.PaymentsId;

public interface IPaymentService {
	
	public Payment getPayment();
	public void savePayment(Payment payment);
	public Payment getPaymentById(PaymentsId id);
	public List<Payment> getAllPayment();
	public void deletePaymentById(PaymentsId id);
	//public List<Payment> getPayments(String var);
	
}
