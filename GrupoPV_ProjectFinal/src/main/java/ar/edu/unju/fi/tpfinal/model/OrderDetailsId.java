package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderDetailsId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "product_code")
	private Products productCode;
	
	@ManyToOne
	@JoinColumn(name = "order_number")
	private Orders orderNumber;
	
	//------ CONSTRUCTORES -------

	public OrderDetailsId() {
		// TODO Auto-generated constructor stub
		super();
	}
	

	/**
	 * Constructor Parametrizado
	 * 
	 * @param productCode
	 * @param orderNumber
	 */

	public OrderDetailsId(Products productCode, Orders orderNumber) {
		super();
		this.productCode = productCode;
		this.orderNumber = orderNumber;
	}

	//----- METODOS ACCESORES ------	
	
	public Products getProductCode() {
		return productCode;
	}

	public void setProductCode(Products productCode) {
		this.productCode = productCode;
	}

	public Orders getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Orders orderNumber) {
		this.orderNumber = orderNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	//ToString

	@Override
	public String toString() {
		return "OrderDetailsId [productCode=" + productCode + ", orderNumber=" + orderNumber + "]";
	}


	
	

}
