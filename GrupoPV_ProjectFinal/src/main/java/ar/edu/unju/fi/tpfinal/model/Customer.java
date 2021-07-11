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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "CUSTOMERS")
@Component
public class Customer {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_number")
	@NotBlank(message ="You must enter a number")
	@Size(min = 1,max = 30, message="Enter a minimum of 3 characters and a maximum of 30")
	private String customerNumber;
	
	@Column(name = "customer_name")
	@NotBlank(message ="You must enter a name")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String customerName;
	
	@Column(name = "contact_last_name")
	@NotBlank(message ="You must enter a contact last name")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String contactLastName;
	
	@Column(name = "contact_first_name")
	@NotBlank(message ="You must enter a contact first name")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String contactFirstName;
	
	@Column(name = "phone")
	@NotBlank(message ="You must enter a phone number")
	@Size(min = 3,max = 20, message="Enter a minimum of 3 characters and a maximum of 20")
	private String phone;
	
	@Column(name = "address_line1")
	@NotBlank(message ="You must enter an address")
	@Size(min = 3,max = 20, message="Enter a minimum of 3 characters and a maximum of 20")
	private String addressLine1;
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "city")
	@NotBlank(message ="You must enter a city")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String city;
	
	@Column(name = "state")
	@NotBlank(message ="You must enter a state")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String state;
	
	@Column(name = "postal_code")
	@NotBlank(message ="You must enter a code postal")
	@Size(min = 3,max = 20, message="Enter a minimum of 3 characters and a maximum of 20")
	private String postalCode;
	
	@Column(name = "country")
	@NotBlank(message ="You must enter a country")
	@Size(min = 3,max =50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String country;
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_number")
	private Employee salesRepEmployeeNumber;
	
	@Column(name = "credit_limit")
	@NotNull(message = "You must enter a credito limit")
	@Min(value=100,message="You must enter a value greater than 100")
	private Double creditLimit;

	
	//------ CONSTRUCTORES -------
	
	public Customer() {
	}

	public Customer(String customerNumber, String customerName, String contactLastName, String contactFirstName,
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

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContactLastName() {
		return contactLastName;
	}

	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}

	public String getContactFirstName() {
		return contactFirstName;
	}

	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Employee getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	public void setSalesRepEmployeeNumber(Employee salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Override
	public String toString() {
		return "Customer [customerNumber=" + customerNumber + ", customerName=" + customerName + ", contactLastName="
				+ contactLastName + ", contactFirstName=" + contactFirstName + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state
				+ ", postalCode=" + postalCode + ", country=" + country + ", salesRepEmployeeNumber="
				+ salesRepEmployeeNumber + ", creditLimit=" + creditLimit + "]";
	}
	
}
