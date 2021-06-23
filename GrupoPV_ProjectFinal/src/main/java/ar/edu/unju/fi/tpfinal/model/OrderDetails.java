package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "ORDERDETAILS")
@Component
public class OrderDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private OrderDetailsId id;
	/*
	@Column(name = "ORDER_NUMBER")
	private Integer orderNumber;
	*/
	
	@Column(name = "PRODUCT_CODE")
	private String productCode;
	
	@Column(name = "QUANTITY_ORDERED")
	private Integer quantityOrdered;
	
	@Column(name = "PRICE_EACH")
	private Double priceEach;
	
	@Column(name = "ORDER_LINE_NUMBER")
	private Short orderLineNumber;
	
	
	//------ CONSTRUCTORES -------
	

	public OrderDetails() {
		super();
	}

	public OrderDetails(OrderDetailsId id, Integer orderNumber, String productCode, Integer quantityOrdered,
			Double priceEach, Short orderLineNumber) {
		super();
		this.id = id;
	//	this.orderNumber = orderNumber;
		this.productCode = productCode;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}

	

	//----- METODOS ACCESORES ------


/*

	public Integer getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

*/
	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}


	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}


	public Double getPriceEach() {
		return priceEach;
	}


	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}


	public Short getOrderLineNumber() {
		return orderLineNumber;
	}


	public void setOrderLineNumber(Short orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	
	

	public OrderDetailsId getId() {
		return id;
	}



	public void setId(OrderDetailsId id) {
		this.id = id;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
/*
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", orderNumber=" + orderNumber + ", productCode=" + productCode
				+ ", quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach + ", orderLineNumber="
				+ orderLineNumber + "]";
	}

*/
}

