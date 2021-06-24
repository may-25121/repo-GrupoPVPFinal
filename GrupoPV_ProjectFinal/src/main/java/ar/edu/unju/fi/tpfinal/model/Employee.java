package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "EMPLOYEES")
@Component
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_number")
	private Integer employeeNumber;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "extension")
	private String extension;
	
	@Column(name = "email")
	private String email;
	
	//RELACION CON LA CLASE OFFICE
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "office_code")
	private Office officeCode;
	
	@Column(name = "job_title")
	private String jobTitle;
	
	//RELACION CONSIGO MISMA
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reportsTo")
	private Employee employee;
	
	//------ CONSTRUCTORES -------
	
	public Employee() {
	}

	public Employee(Integer employeeNumber, String lastName, String firstName, String extension, String email,
			Office officeCode, String jobTitle, Employee employee) {
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.officeCode = officeCode;
		this.jobTitle = jobTitle;
		this.employee = employee;
	}

	//----- METODOS ACCESORES ------

	/**
	 * @return EmployeeNumber
	 */

	
	public Integer getEmployeeNumber() {
		return employeeNumber;
	}
	/**
	 * @param status the setEmployeeNumber
	 */

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	/**
	 * @return lastName
	 */

	public String getLastName() {
		return lastName;
	}
	/**
	 * @param status the setEmployeeNumber
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return firstName
	 */

	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param status the setFirstName
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return extension
	 */
	
	public String getExtension() {
		return extension;
	}
	/**
	 * @param status the setExtension
	 */

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	/**
	 * @return email
	 */

	public String getEmail() {
		return email;
	}

	/**
	 * @param setEmail
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return officeCode
	 */
	
	public Office getOfficeCode() {
		return officeCode;
	}
	/**
	 * @param status the setOfficeCode
	 */

	public void setOfficeCode(Office officeCode) {
		this.officeCode = officeCode;
	}

	/**
	 * @return jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	/**
	 * @param status the setJobTitle
	 */

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	//ToString
	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", extension=" + extension + ", email=" + email + ", officeCode=" + officeCode + ", jobTitle=" + jobTitle + "]";
	}
	
}
