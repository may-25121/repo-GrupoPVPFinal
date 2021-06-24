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
	 * Constructor Parametrizado
	 * 
	 * @param quantityOrdered
	 * @param priceEach
	 * @param orderLineNumber
	 * @param orderNumber
	 * @param orderCode
	 *
	 */
	

	public OrderDetails(OrderDetailsId id, Integer quantityOrdered, Double priceEach, Short orderLineNumber) {
		super();
		this.id = id;
		this.quantityOrdered = quantityOrdered;
		this.priceEach = priceEach;
		this.orderLineNumber = orderLineNumber;
	}



	//----- METODOS ACCESORES ------






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


	//TO STRING
	
	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", quantityOrdered=" + quantityOrdered + ", priceEach=" + priceEach
				+ ", orderLineNumber=" + orderLineNumber + "]";
	}

	
	
	


	
}

