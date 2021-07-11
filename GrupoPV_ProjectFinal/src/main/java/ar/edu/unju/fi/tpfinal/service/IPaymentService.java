package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Payment;

public interface IPaymentService {
	
	public Payment getPayment();
	public void savePayment(Payment payment);
	public Payment getPaymentById(String id);
	public List<Payment> getAllPayment();
	public void deletePaymentById(String id);
	public List<Payment> getPayment(String var);
	
}
