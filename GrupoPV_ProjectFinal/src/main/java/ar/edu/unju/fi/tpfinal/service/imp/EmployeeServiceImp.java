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
		Employee employee = employeeDAO.findById(id).get();
		return employee;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees = (List<Employee>) employeeDAO.findAll();
		return employees;
	}

	@Override
	public void deteEmployeById(int id) {
		employeeDAO.deleteById(id);
	}

	@Override
	public List<Employee> getEmployees(String employeenumber, String lastname, String jobtitle) {
		List<Employee> employees = new ArrayList<>();
		if(!employeenumber.isEmpty() && !employeeDAO.findById(Integer.valueOf(employeenumber)).isEmpty()) {
			employees.add(employeeDAO.findById(Integer.valueOf(employeenumber)).get());
		}else if(!lastname.isEmpty() && !jobtitle.isEmpty()) {
			if(!employeeDAO.findByLastName(lastname).isEmpty() && !employeeDAO.findByJobTitle(jobtitle).isEmpty()) {
				employees = employeeDAO.findByLastNameAndJobTitle(lastname, jobtitle);
			}else if(!employeeDAO.findByLastName(lastname).isEmpty() || !employeeDAO.findByJobTitle(jobtitle).isEmpty()) {
				employees = employeeDAO.findByLastNameOrJobTitle(lastname, jobtitle);
			}else {
				employees = (List<Employee>) employeeDAO.findAll();
			}	
		}else if((!lastname.isEmpty() && !employeeDAO.findByLastName(lastname).isEmpty()) || (!jobtitle.isEmpty() && !employeeDAO.findByJobTitle(jobtitle).isEmpty())) {
			employees = employeeDAO.findByLastNameOrJobTitle(lastname, jobtitle);
		}else {
			employees = (List<Employee>) employeeDAO.findAll();
		}
		return employees;
	}

}
