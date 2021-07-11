package ar.edu.unju.fi.tpfinal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "PAYMENTS")
@Component
public class Payment {
	
	//@EmbeddedId
	//private PaymentsId paymentsId;
	
	@Id
	@Column//(name = "check_number")
	@NotBlank(message = "El campo no puede estar vacio")
	private String checkNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "payment_date")
	@NotNull(message = "El campo no puede estar vacio")
	private LocalDate paymentDate;
	
	@Column(name = "amount")
	@NotNull(message = "El campo no puede estar vacio")
	private Double amount;
	
	@Autowired
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="customerNumber")
	@NotNull(message = "El campo no puede estar vacio")
	private Customer customers;
	
	//------ CONSTRUCTORES -------
	
	public Payment() {
	}
	
	public Payment(@NotBlank(message = "El campo no puede estar vacio") String checkNumber,
			@NotNull(message = "El campo no puede estar vacio") LocalDate paymentDate,
			@NotNull(message = "El campo no puede estar vacio") Double amount,
			@NotNull(message = "El campo no puede estar vacio") Customer customers) {
		super();
		this.checkNumber = checkNumber;
		this.paymentDate = paymentDate;
		this.amount = amount;
		this.customers = customers;
	}


	//----- METODOS ACCESORES ------
	
	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

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

	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Payment [checkNumber=" + checkNumber + ", paymentDate=" + paymentDate + ", amount=" + amount
				+ ", customers=" + customers + "]";
	}
		
	
}
