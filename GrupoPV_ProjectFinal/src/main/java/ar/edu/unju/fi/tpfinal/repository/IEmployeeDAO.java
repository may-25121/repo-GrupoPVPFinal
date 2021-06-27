package ar.edu.unju.fi.tpfinal.repository;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Employee;

public interface IEmployeeDAO extends CrudRepository<Employee,Integer> {

}
