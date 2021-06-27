package ar.edu.unju.fi.tpfinal.service.imp;

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
		Employee employee = employeeDAO.findById(id).get();
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employee = (List<Employee>) employeeDAO.findAll();
		return employee;
	}

	@Override
	public void deteEmployeById(int id) {
		employeeDAO.deleteById(id);
	}

}
