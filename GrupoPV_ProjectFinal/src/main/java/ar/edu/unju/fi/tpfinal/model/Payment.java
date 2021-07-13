package ar.edu.unju.fi.tpfinal.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;


@Entity
@Table(name = "PAYMENTS")
@Component
public class Payment {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PaymentsId id;
	
	/*@Id
	@Column//(name = "check_number")
	@NotBlank(message = "El campo no puede estar vacio")
	private String checkNumber;*/
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "payment_date")
	@NotNull(message = "The field cannot be empty")
	private LocalDate paymentDate;
	
	@Column(name = "amount")
	@NotNull(message = "The field cannot be empty")
	private Double amount;
	
	/*@Autowired
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="customerNumber")
	@NotNull(message = "El campo no puede estar vacio")
	private Customer customers;*/
	
	//------ CONSTRUCTORES -------
	
	public Payment() {
	}

	public Payment(PaymentsId id, LocalDate paymentDate, Double amount) {
		this.id = id;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public PaymentsId getId() {
		return id;
	}

	public void setId(PaymentsId id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
