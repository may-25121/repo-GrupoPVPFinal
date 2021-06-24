package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "CUSTOMERS")
@Component
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_number")
	private Integer customerNumber;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "contact_last_name")
	private String contactLastName;
	
	@Column(name = "contact_first_name")
	private String contactFirstName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address_line1")
	private String addressLine1;
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "country")
	private String country;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_number")
	private Employee salesRepEmployeeNumber;
	
	@Column(name = "credit_limit")
	private Double creditLimit;

	
	//------ CONSTRUCTORES -------
	
	/**
	 * Constructor por defecto
	 */
	
	public Customer() {
	}
	/**
	 * Constructor parametrizado
	 * 
	 * @param customerNumber
	 * @param customerName
	 * @param contactLastName
	 * @param contactFirstName
	 * @param phone
	 * @param ddressLine1
	 * @param addressLine2
	 * @param city
	 * @param state
	 */

	public Customer(Integer customerNumber, String customerName, String contactLastName, String contactFirstName,
			String phone, String addressLine1, String addressLine2, String city, String state, String postalCode,
			String country, Employee salesRepEmployeeNumber, Double creditLimit) {
		this.customerNumber = customerNumber;
		this.customerName = customerName;
		this.contactLastName = contactLastName;
		this.contactFirstName = contactFirstName;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.state = state;
		this.postalCode = postalCode;
		this.country = country;
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
		this.creditLimit = creditLimit;
	}
	
	//----- METODOS ACCESORES ------
	
	/**
	 * @return the CustomerNumber
	 */

	public Integer getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @param status the setCustomerNumber
	 */
	public void setCustomerNumber(Integer customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	/**
	 * @return the CustomerName
	 */

	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param status the setCustomerName
	 */
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the ContactLastName
	 */

	public String getContactLastName() {
		return contactLastName;
	}
	
	/**
	 * @param status the setContactLastName
	 */
	
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}
	/**
	 * @return the ContactFirstName
	 */


	public String getContactFirstName() {
		return contactFirstName;
	}
	/**
	 * @param status the setContactFirstName
	 */

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}
	
	/**
	 * @return the Phone
	 */


	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param status the setPhone
	 */

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * @return the AddressLine1
	 */


	public String getAddressLine1() {
		return addressLine1;
	}
	
	/**
	 * @param status the setAddressLine1
	 */

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	
	/**
	 * @return the AddressLine2
	 */


	public String getAddressLine2() {
		return addressLine2;
	}
	
	/**
	 * @param status the setAddressLine2
	 */
	
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	
	/**
	 * @return the City
	 */


	public String getCity() {
		return city;
	}
	
	/**
	 * @param status the setCity
	 */

	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return the State
	 */


	public String getState() {
		return state;
	}
	
	/**
	 * @param status the setState
	 */

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the PostalCode
	 */

	public String getPostalCode() {
		return postalCode;
	}
	
	/**
	 * @param status setPostalCode
	 */

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the Country
	 */

	public String getCountry() {
		return country;
	}
	
	/**
	 * @param status setCountry
	 */

	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the SalesRepEmployeeNumber
	 */

	public Employee getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}
	
	/**
	 * @param status setSalesRepEmployeeNumber
	 */

	public void setSalesRepEmployeeNumber(Employee salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}
	
	/**
	 * @return the getCreditLimit
	 */


	public Double getCreditLimit() {
		return creditLimit;
	}
	
	/**
	 * @param status setCreditLimit
	 */

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}
//	ToString
	
	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactLastName="
				+ contactLastName + ", contactFirstName=" + contactFirstName + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + ", salesRepEmployeeNumber="
				+ salesRepEmployeeNumber + ", creditLimit=" + creditLimit + "]";
	}
	
}
