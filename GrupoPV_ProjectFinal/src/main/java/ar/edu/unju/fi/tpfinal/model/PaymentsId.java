package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Embeddable
public class PaymentsId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Claves Combinadas
	
	@OneToOne
	@JoinColumn(name = "customer_number")
	private Customer customerNumber;
	
	
	private String checkNumber;

	//-----CONSTRUCTOR-----
	/**
	 * Constructor por defecto
	 */
	public PaymentsId() {
	}
	
	/**
	 * Constructor parametrizado
	 */

	public PaymentsId(Customer customerNumber, String checkNumber) {
		this.customerNumber = customerNumber;
		this.checkNumber = checkNumber;
	}

    //------METODOS ACCESORES-----
	
	/**
	 * @return customerNumber
	 */
	
	public Customer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param status the setCustomerNumber
	 */

	public void setCustomerNumber(Customer customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	/**
	 * @return checkNumber
	 */

	public String getCheckNumber() {
		return checkNumber;
	}
	/**
	 * @param status the setCheckNumber
	 */

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
