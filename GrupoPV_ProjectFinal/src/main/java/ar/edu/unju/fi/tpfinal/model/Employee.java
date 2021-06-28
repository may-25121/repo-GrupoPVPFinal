package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "EMPLOYEES")
@Component
public class Employee {
	
	@Id
	@Column(name = "employee_number")
	private Integer employeeNumber;
	
	@Column(name = "last_name")
	@NotBlank(message ="You must enter a last name")
	private String lastName;
	
	@Column(name = "first_name")
	@NotBlank(message ="You must add a first name")
	private String firstName;
	
	@Column(name = "extension")
	@NotBlank(message ="You must add an extension")
	private String extension;
	
	@Column(name = "email")
	@NotBlank(message = "You must enter a valid email.")
	@Email(message = "Enter a valid email")
	private String email;
	
	//RELATIONSHIP WITH THE OFFICE CLASS
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "office_code")
	@NotNull(message ="You must select an office")
	private Office officeCode;
	
	@Column(name = "job_title")
	@NotBlank(message ="You must add a job title")
	private String jobTitle;
	
	//RELATIONSHIP WITH YOURSELF
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reportsTo")
	private Employee employee;
	
	//RELATIONSHIP WITH THE USER
	@Valid
	@Autowired
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	private User user;
	
	//------ CONSTRUCTORES -------
	
	public Employee() {
	}

	public Employee(Integer employeeNumber, String lastName, String firstName, String extension, String email,
			Office officeCode, String jobTitle, Employee employee, User user) {
		this.employeeNumber = employeeNumber;
		this.lastName = lastName;
		this.firstName = firstName;
		this.extension = extension;
		this.email = email;
		this.officeCode = officeCode;
		this.jobTitle = jobTitle;
		this.employee = employee;
		this.user = user;
	}

	//----- METODOS ACCESORES ------

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Office getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(Office officeCode) {
		this.officeCode = officeCode;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	//----- METODO TOSTRING------
	
	@Override
	public String toString() {
		return "Employee [employeeNumber=" + employeeNumber + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", extension=" + extension + ", email=" + email + ", officeCode=" + officeCode + ", jobTitle=" + jobTitle + "]";
	}
	
}
