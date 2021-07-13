package ar.edu.unju.fi.tpfinal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "ORDERDETAILS")
@Component
public class OrderDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OrderDetailsId id;
	
	@Column(name = "quantity_ordered")
	@NotNull(message = "You must enter a quantity ordered")
	@Min(value=1,message="minimum 1 digit")
	private Integer quantityOrdered;
	
	@Column(name = "price_each")
	@NotNull(message = "You must enter a price each")
	@Min(value=1,message="minimum 1 digit")
	private Double priceEach;
	
	@Column(name = "order_line_number")
	@NotNull(message = "You must enter a order line number")
	@Min(value=1,message="minimum 1 digit")
	private Integer orderLineNumber;
	
	
	//------ CONSTRUCTORES -------
	

	public OrderDetails() {
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
	
	public OrderDetails(OrderDetailsId id, Integer quantityOrdered, Double priceEach, Integer orderLineNumber) {
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

	public Integer getOrderLineNumber() {
		return orderLineNumber;
	}

	public void setOrderLineNumber(Integer orderLineNumber) {
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

