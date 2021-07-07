package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Employee;

public interface IEmployeeService {
	
	public Employee getEmployee();
	public void saveEmployee(Employee employee);
	public Employee getEmployeeById(int id);
	public List<Employee> getAllEmployees();
	public void deteEmployeById(int id);
	public List<Employee> getEmployees(String var);

}
