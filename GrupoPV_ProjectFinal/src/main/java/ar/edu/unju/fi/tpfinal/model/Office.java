package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "OFFICES")
@Component
public class Office {
	
	@Id
	@Column(name = "office_code")
	@NotBlank(message ="You must enter a code")
	@Size(min = 3,max = 20, message="Enter a minimum of 3 characters and a maximum of 20")
	private String officeCode;
	
	@Column(name = "city")
	@NotBlank(message ="You must add the city")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String city;
	
	@Column(name = "phone")
	@NotBlank(message ="You must add phone")
	@Size(min = 7,max = 20, message="Enter a minimum of 7 characters and a maximum of 20")
	private String phone;
	
	@Column(name = "address_line1")
	@NotBlank(message ="You must add an address")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String addressLine1;
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	@NotBlank(message ="You must add a country")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String country;
	
	@Column(name = "postal_code")
	@NotBlank(message ="You must add a zip code")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String postalCode;
	
	@Column(name = "territory")
	@NotBlank(message ="You must add phone a territory")
	@Size(min = 3,max = 50, message="Enter a minimum of 3 characters and a maximum of 50")
	private String territory;
	
	//------ CONSTRUCTORES -------
	
	public Office() {
	}

	public Office(String officeCode, String city, String phone, String addressLine1, String addressLine2, String state,
			String country, String postalCode, String territory) {
		this.officeCode = officeCode;
		this.city = city;
		this.phone = phone;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.country = country;
		this.postalCode = postalCode;
		this.territory = territory;
	}

	//----- METODOS ACCESORES ------
	
	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}
	
	//----- METODO TOSTRING------
	
	@Override
	public String toString() {
		return "Office [ officeCode=" + officeCode + ", city=" + city + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state + ", country=" + country
				+ ", postalCode=" + postalCode + ", territory=" + territory + "]";
	}

}
