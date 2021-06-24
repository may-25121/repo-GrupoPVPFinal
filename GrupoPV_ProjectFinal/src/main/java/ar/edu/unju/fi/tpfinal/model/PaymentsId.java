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
	
	@JoinColumn(name = "check_number")
	private String checkNumber;

	public PaymentsId() {
	}

	public PaymentsId(Customer customerNumber, String checkNumber) {
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
	}


	public Customer getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
