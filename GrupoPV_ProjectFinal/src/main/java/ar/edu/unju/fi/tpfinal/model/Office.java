package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "OFFICES")
@Component
public class Office {
	
	@Id
	@Column(name = "office_code")
	private String officeCode;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "address_line1")
	private String addressLine1;
	
	@Column(name = "address_line2")
	private String addressLine2;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "postal_code")
	private String postalCode;
	
	@Column(name = "territory")
	private String territory;
	
	//------ CONSTRUCTORES -------
	
	public Office() {
	}
	/**
	 * Constructor parametrizado
	 * 
	 * @param officeCode
	 * @param city
	 * @param phone
	 * @param addressLine1
	 * @param addressLine2
	 * @param state
	 * @param country
	 * @param postalCode
	 * @param territory
	 */

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

	/**
	 * @return officeCode
	 */
	
	public String getOfficeCode() {
		return officeCode;
	}
	/**
	 * @param status the setOfficeCode
	 */

	

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	
	/**
	 * @return city
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
	 * @return phone
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
	 * @return addressLine1
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
	 * @return addressLine2
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
	 * @return state
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
	 * @return country
	 */
	
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param status the setCountry
	 */


	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return postalCode
	 */
	
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param status the setPostalCode
	 */

	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	/**
	 * @return territory
	 */
	

	public String getTerritory() {
		return territory;
	}
	
	/**
	 * @param status the setTerritory
	 */


	public void setTerritory(String territory) {
		this.territory = territory;
	}
	
//ToString	

	@Override
	public String toString() {
		return "Office [officeCode=" + officeCode + ", city=" + city + ", phone=" + phone + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state + ", country=" + country
				+ ", postalCode=" + postalCode + ", territory=" + territory + "]";
	}

}
