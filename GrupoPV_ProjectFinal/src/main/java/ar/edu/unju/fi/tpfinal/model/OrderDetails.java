package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "ORDERDETAILS")
@Component
public class OrderDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	private OrderDetailsId id;
	
	@Column(name = "QUANTITY_ORDERED")
	private Integer quantityOrdered;
	
	@Column(name = "PRICE_EACH")
	private Double priceEach;
	
	@Column(name = "ORDER_LINE_NUMBER")
	private Short orderLineNumber;
	
	
	//------ CONSTRUCTORES -------
	

	public OrderDetails() {
		// TODO Auto-generated constructor stub
		super();
	}

	

	/**
	 * 
	 * @param id
	 * @param quantityOrdered
	 * @param priceEach
	 * @param orderLineNumber
	 */
	

	public OrderDetails(OrderDetailsId id, Integer quantityOrdered, Double priceEach, Short orderLineNumber) {
		super();
		this.id = id;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}



	//----- METODOS ACCESORES ------



	/**
	 * @return quantityOrdered
	 */


	public Integer getQuantityOrdered() {
		return quantityOrdered;
	}
	
	/**
	 * @param status the setQuantityOrdered
	 */

	public void setQuantityOrdered(Integer quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	/**
	 * @return priceEach
	 */
	
	public Double getPriceEach() {
		return priceEach;
	}
	
	/**
	 * @param status the setPriceEach
	 */

	public void setPriceEach(Double priceEach) {
		this.priceEach = priceEach;
	}
	/**
	 * @return orderLineNumber
	 */

	public Short getOrderLineNumber() {
		return orderLineNumber;
	}
	
	/**
	 * @param status the setOrderLineNumber
	 */

	public void setOrderLineNumber(Short orderLineNumber) {
		this.orderLineNumber = orderLineNumber;
	}

	
	/**
	 * @return id
	 */

	public OrderDetailsId getId() {
		return id;
	}

	/**
	 * @param status the setId
	 */

	public void setId(OrderDetailsId id) {
		this.id = id;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	//TO STRING
	
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach
				+ ", orderLineNumber=" + orderLineNumber + "]";
	}

	
	
	


	
}

