package ar.edu.unju.fi.tpfinal.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.unju.fi.tpfinal.model.Employee;
import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Controller
public class EmployeeController {
	
	private static final Log LOGGER=LogFactory.getLog(EmployeeController.class);
	
	@Autowired
	Employee employee;
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService;
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService;
	
	@GetMapping("/empleado/nuevo")
	public String getNuevoEmployeePage(Model model) {
		LOGGER.info("CONTROLLER : EmployeeController con /empleado/nuevo invoca al metodo get");
		LOGGER.info("METHOD : getNuevoEmployeePage()");
		LOGGER.info("RESULT : Se visualiza la pagina nuevoempleado.html");
		model.addAttribute("employee", employeeService.getEmployee());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("offices", officeService.getAllOffices());
		return "nuevoempleado";
	}
	
	@PostMapping("/empleado/guardar")
	public String saveEmployeePage(@ModelAttribute("employee") Employee employee, Model model, @RequestParam(name="officeCode") String officeCode, @RequestParam(name="numeremplo") String numeremplo) {
			employee.setOfficeCode(this.officeService.getOfficeById(officeCode));
			
			if(Integer.valueOf(numeremplo)==0) {
				employee.setEmployee(null);
			}else {
				employee.setEmployee(this.employeeService.getEmployeeById(Integer.valueOf(numeremplo)));
			}
			
		try {
			employeeService.saveEmployee(employee);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("employees", employeeService.getAllEmployees());

		return "listarempleados";
	}
	
	@GetMapping("/empleado/listar")
	public String getListarEmployeePage(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "listarempleados";
	}
	

}
