package ar.edu.unju.fi.tpfinal.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "USERS")
@Component
public class User {
	
	@Id
	@Column(name = "user_id")
	private Integer id;
	
	@Column(name = "user_username")
	private String username;
	
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "user_role")
	private String role;
	
	@Autowired
	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Employee employeeUser;
	
	//------ CONSTRUCTORES -------

	public User() {
	}

	public User(Integer id, String username, String password, String role, Employee employeeUser) {
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
