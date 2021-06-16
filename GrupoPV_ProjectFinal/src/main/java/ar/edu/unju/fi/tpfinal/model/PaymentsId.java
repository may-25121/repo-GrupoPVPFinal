package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Embeddable
public class PaymentsId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "customer_number")
	private Customer customerNumber;
	
	@OneToOne
	@JoinColumn(name = "check_number")
	private Payment checkNumber;

	public PaymentsId() {
	}

	public PaymentsId(Customer customerNumber, Payment checkNumber) {
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
	}

	public Customer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Payment getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(Payment checkNumber) {
		this.checkNumber = checkNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
