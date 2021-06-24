package ar.edu.unju.fi.tpfinal.model;



import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	 @Autowired
	 @ManyToOne
	 @JoinColumn(name = "customerNumber" )
	 private Customer customers;
	 
	 
	 
	
	//------ CONSTRUCTORES -------
	
	public Orders() {
		// TODO Auto-generated constructor stub
		super();
	}
	

	/**
	 * Constructor Parametrizado
	 * 
	 * 
	 * @param orderNumber
	 * @param orderDate
	 * @param requiredDate
	 * @param shippedDate
	 * @param status
	 * @param comments
	 * @param customers
	 */
	
	

	public Orders(Integer orderNumber, LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate,
			String status, String comments, Customer customers) {
		super();
		this.orderNumber = orderNumber;
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.status = status;
		this.comments = comments;
		this.customers = customers;
	}




	//----- METODOS ACCESORES ------

	
	/**
	 * @return the orderNumber
	 */

	public Integer getOrderNumber() {
		return orderNumber;
	}


	
	/**
	 * @param orderNumber the orderNumber to set
	 */

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}


	/**
	 * @return the orderDate
	 */
	
	
	public LocalDate getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the requiredDate
	 */
	
	public LocalDate getRequiredDate() {
		return requiredDate;
	}


	/**
	 * @param requiredDate the requiredDate to set
	 */

	public void setRequiredDate(LocalDate requiredDate) {
		this.requiredDate = requiredDate;
	}

	/**
	 * @return the shippedDate
	 */

	public LocalDate getShippedDate() {
		return shippedDate;
	}
	
	/**
	 * @param shippedDate the shippedDate to set
	 */


	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	/**
	 * @return the status
	 */

	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */

	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * @return the comments
	 */

	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */

	public void setComments(String comments) {
		this.comments = comments;
	}


	 public Customer getCustomer() {
		return customers;
	}


	public void setCustomer(Customer customer) {
		this.customers = customer;
	}

	//ToString
	
	@Override
	public String toString() {
		return "Orders [orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate
				+ ", shippedDate=" + shippedDate + ", status=" + status + ", comments=" + comments + ", customers="
				+ customers + "]";
	}


}
