package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "USERS")
@Component
public class Usser {
	
	@Id
	@Column(name = "user_id")
	@NotNull(message = "You must enter an id")
	private Integer id;
	
	@Column(name = "user_username")
	@NotEmpty(message="The field cannot be empty")
	@Size(min = 3,max = 20, message="Enter a minimum of 3 characters and a maximum of 20")
	private String username;
	
	@Column(name = "user_password")
	@NotEmpty(message="The field cannot be empty")
	@Size(min = 3,max = 100, message="Enter a minimum of 3 characters and a maximum of 20")
	private String password;
	
	@Column(name = "user_role")
	private String role;
	
	@Autowired
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Employee employeeUser;
	
	//------ CONSTRUCTORES -------

	public Usser() {
	}

	public Usser(Integer id, String username, String password, String role, Employee employeeUser) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.employeeUser = employeeUser;
	}
	
	//----- METODOS ACCESORES ------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Employee getEmployeeUser() {
		return employeeUser;
	}

	public void setEmployeeUser(Employee employeeUser) {
		this.employeeUser = employeeUser;
	}

	//----- METODO TOSTRING------
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", employeeUser=" + employeeUser + "]";
	}

	

}
