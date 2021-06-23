package ar.edu.unju.fi.tpfinal.model;



import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;



@Entity
@Table(name="ORDERS")
@Component
public class Orders {
	
	@Id
	@Column(name = "ORDER_NUMBER")
	private Integer orderNumber;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "ORDER_DATE")
	private LocalDate orderDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "REQUIRED_DATE")
	private LocalDate requiredDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name = "SHIPPED_DATE")
	private LocalDate shippedDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	/*@Column(name = "CUSTOMER_NUMBER")
	private Integer customerNumber;
	*/
	
	
	/* @Autowired
	 @ManyToOne
	 @JoinColumn(name = "order_details")
	private OrderDetails orderDetails;
	 */
	 @Autowired
	 @ManyToOne
	 @JoinColumn(name = "customerNumber" )
	 private Customer customer;
	 
	 
	 
	
	//------ CONSTRUCTORES -------
	
	public Orders() {
		super();
	}


	public Orders(Integer orderNumber, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
			String status, String comments, Integer customerNumber, OrderDetails orderDetails, Customer customer) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		//this.customerNumber = customerNumber;
		//this.orderDetails = orderDetails;
		this.customer = customer;
	}






	//----- METODOS ACCESORES ------

	


	public Integer getOrderNumber() {
		return orderNumber;
	}


	

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public LocalDate getRequiredDate() {
		return requiredDate;
	}


	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}


	public LocalDate getShippedDate() {
		return shippedDate;
	}


	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}

/*
	public Integer getCustomerNumber() {
		return customerNumber;
	}


	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
*/
/*

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}




	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

*/


	 public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	@Override
	public String toString() {
		return "Orders [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customerNumber="
				+  ", orderDetails="  + ", customer=" + customer + "]";
	}


}
