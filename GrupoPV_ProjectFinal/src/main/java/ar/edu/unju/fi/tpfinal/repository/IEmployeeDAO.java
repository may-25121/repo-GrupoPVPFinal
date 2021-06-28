package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Employee;

public interface IEmployeeDAO extends CrudRepository<Employee,Integer> {

	public List<Employee> findByLastName(String lastname);
	public List<Employee> findByJobTitle(String jobtitle);
	public List<Employee> findByLastNameOrJobTitle(String lastname, String jobtitle);
	public List<Employee> findByLastNameAndJobTitle(String lastname, String jobtitle);
	
}
