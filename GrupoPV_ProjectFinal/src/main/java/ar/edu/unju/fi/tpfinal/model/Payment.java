package ar.edu.unju.fi.tpfinal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "PAYMENTS")
@Component
public class Payment {
	
	@EmbeddedId
	private PaymentsId paymentsId;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@Column(name = "amount")
	private Double amount;
	
	//------ CONSTRUCTORES -------
	
	public Payment() {
	}

	public Payment(PaymentsId paymentsId, LocalDate paymentDate, Double amount) {
		this.paymentsId = paymentsId;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	//----- METODOS ACCESORES ------

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [paymentsId=" + paymentsId + ", paymentDate=" + paymentDate + ", amount=" + amount + "]";
	}

	
	
	
}
