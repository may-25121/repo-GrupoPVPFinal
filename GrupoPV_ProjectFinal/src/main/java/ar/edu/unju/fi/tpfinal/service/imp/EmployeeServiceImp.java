package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Employee;
import ar.edu.unju.fi.tpfinal.repository.IEmployeeDAO;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;

@Service("employeeServiceMysql")
public class EmployeeServiceImp implements IEmployeeService {
	
	@Autowired
	IEmployeeDAO employeeDAO;
	
	@Autowired
	private Employee employee;

	@Override
	public Employee getEmployee() {
		return this.employee;
	}

	@Override
	public void saveEmployee(Employee employee) {
		employeeDAO.save(employee);
	}

	@Override
	public Employee getEmployeeById(int id) {
		Employee employee = employeeDAO.findById(String.valueOf(id)).get();
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = (List<Employee>) employeeDAO.findAll();
		return employees;
	}

	@Override
	public void deteEmployeById(int id) {
		employeeDAO.deleteById(String.valueOf(id));
	}

	@Override
	public List<Employee> getEmployees(String var) {
		List<Employee> employees = new ArrayList<>();
		if(!var.isEmpty() && !employeeDAO.findById(var).isEmpty()) {
			employees.add(employeeDAO.findById(var).get());
		}else if(!var.isEmpty() && !employeeDAO.findByLastName(var).isEmpty()) {
			employees = employeeDAO.findByLastName(var);
		}else if(!var.isEmpty() && !employeeDAO.findByJobTitle(var).isEmpty()) {
			employees = employeeDAO.findByJobTitle(var);
		}else {
			employees = (List<Employee>) employeeDAO.findAll();
		}
		return employees;
	}

}
