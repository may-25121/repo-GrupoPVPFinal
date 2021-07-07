package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Employee;

public interface IEmployeeDAO extends CrudRepository<Employee,String> {

	public List<Employee> findByLastName(String var);
	public List<Employee> findByJobTitle(String var);
	
}
