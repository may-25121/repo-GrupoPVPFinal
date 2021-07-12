package ar.edu.unju.fi.tpfinal.controller;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.tpfinal.model.Employee;
import ar.edu.unju.fi.tpfinal.model.User;
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
	
	@GetMapping("/employee/new")
	public String getNewEmployeePage(Model model) {
		LOGGER.info("CONTROLLER : EmployeeController con /employee/new invoke the get method");
		LOGGER.info("METHOD : getNewEmployeePage()");
		model.addAttribute("employee", employeeService.getEmployee());
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("offices", officeService.getAllOffices());
		LOGGER.info("RESULT : Page is displayed nuevoempleado.html");
		return "nuevoempleado";
	}
	
	@PostMapping("/employee/save")
	public String saveEmployeePage(@Valid @ModelAttribute("employee") Employee emp, BindingResult result, @RequestParam(name="employeeNumber") String employeeNumber, @RequestParam(name="lastName") String lastName,
		@RequestParam(name="firstName") String firstName, @RequestParam(name="extension") String extension, @RequestParam(name="email") String email, @RequestParam(name="officeCode.officeCode") String officeCode,
		@RequestParam(name="jobTitle") String jobTitle, @RequestParam(name="employee.employeeNumber") String employee, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/save invoke the post method");
		if(result.hasErrors()) {
			model.addAttribute("employee", emp);
			model.addAttribute("employees", employeeService.getAllEmployees());
			model.addAttribute("offices", officeService.getAllOffices());
			LOGGER.info("RESULT : Page is displayed nuevoempleado.html");
			return "nuevoempleado";
		}else {
			Employee employe = new Employee();
			//User user = new User();
			//employe = this.employeeService.getEmployee();
			employe.setEmployeeNumber(employeeNumber);
			employe.setLastName(lastName);
			employe.setFirstName(firstName);
			employe.setExtension(extension);
			employe.setEmail(email);
			employe.setOfficeCode(this.officeService.getOfficeById(officeCode));
			employe.setJobTitle(jobTitle);		
			if(Integer.valueOf(employee)==0) {
				employe.setEmployee(null);
			}else {
				employe.setEmployee(this.employeeService.getEmployeeById(Integer.valueOf(employee)));
			}
			LOGGER.info("METHOD : saveEmployeePage() -- PARAMS: employee'"+employe+"'");
			try {
				employeeService.saveEmployee(employe);
				attribute.addFlashAttribute("success", "The changes were saved successfully!");
			} catch (Exception e) {
				LOGGER.info("EXCEPTION INFO: '"+e+"'");
				attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
			}
			LOGGER.info("RESULT : the page is redirected to /employee/list/");
			return "redirect:/employee/list/";
		}
	}
	
	@GetMapping("/employee/list")
	public String getListEmployeePage(Model model) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/list invoke the get method");
		LOGGER.info("METHOD : getListEmployeePage()");
		model.addAttribute("employees", employeeService.getAllEmployees());
		LOGGER.info("RESULT : Page is displayed listarempleados.html");
		return "listarempleados";
	}
	
	@GetMapping("/employee/edit/{number}")
	public String getEditEmployeePage(@PathVariable("number") int number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/edit/{number} invoke the get method");
		LOGGER.info("METHOD : getEditEmployeePage()");
		if(!employeeService.getCheckEmployeeById(String.valueOf(number))){
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /employee/list/");
			return "redirect:/employee/list/";			
		}
		this.employee = employeeService.getEmployeeById(number);
		model.addAttribute("employee", this.employee);
		LOGGER.info("EMPLOYEE: '" + this.employee.getEmployee()+"'");
		model.addAttribute("employees", employeeService.getAllEmployees());
		model.addAttribute("offices", officeService.getAllOffices());
		LOGGER.info("RESULT : Page is displayed nuevoempleado.html");
		return "nuevoempleado";
	}
	
	@GetMapping("/employee/delete/{number}")
	public String getDeleteEmployeePage(@PathVariable("number") String number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/delete/{number} invoke the get method");
		LOGGER.info("METHOD : getDeleteEmployeePage()");
		if(!employeeService.getCheckEmployeeById(String.valueOf(number))) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /employee/list/");
			return "redirect:/employee/list/";						
		}
		LOGGER.info("RESULT : Page is displayed eliminarempleado.html");
		Employee employee = employeeService.getEmployeeById(Integer.valueOf(number));
		model.addAttribute("employee", employee);
		return "eliminarempleado";
	}
	
	@GetMapping("/employee/delete/confirm/{number}")
	public String getConfirmDeleteEmployeePage(@PathVariable("number") String number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/delete/confirm/{number} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteEmployeePage()");
		try {
			employeeService.deteEmployeById(Integer.valueOf(number));
			attribute.addFlashAttribute("warning", "Record deleted successfully!");
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
		}
		LOGGER.info("RESULT : Page is displayed listarempleados.html");
		return "redirect:/employee/list";
	}
	
	@GetMapping("/employee/search")
	public String getSearchEmployeePage(@RequestParam(name="var") String var, Model model) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/search invoke the get method");
		LOGGER.info("METHOD : getSearchEmployeePage()");
		LOGGER.info("RESULT : Page is displayed listarempleados.html");
		model.addAttribute("employees", employeeService.getEmployees(var));
		return "listarempleados";
	}
	
	
}
