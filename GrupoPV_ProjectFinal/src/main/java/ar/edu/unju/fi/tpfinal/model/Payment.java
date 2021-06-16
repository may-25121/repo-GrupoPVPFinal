package ar.edu.unju.fi.tpfinal.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "PAYMENTS")
@Component
public class Payment {
	
	@EmbeddedId
	private PaymentsId paymentsId;
	
	@Column(name = "payment_date")
	private Date paymentDate;
	
	@Column(name = "amount")
	private Double amount;
	
	//------ CONSTRUCTORES -------
	
	public Payment() {
	}

	public Payment(PaymentsId paymentsId, Date paymentDate, Double amount) {
		this.paymentsId = paymentsId;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	//----- METODOS ACCESORES ------

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
}
