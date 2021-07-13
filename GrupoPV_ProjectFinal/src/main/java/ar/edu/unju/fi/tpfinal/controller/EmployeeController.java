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
import ar.edu.unju.fi.tpfinal.model.Usser;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

/**
 * Clase EmployeeController
 * Clase que responde a los eventos e invoca peticiones de Employee
 * y ademas es el intermediario entre la vista y el modelo .
 * @author Yapura-Anahi
 *
 */
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
	
	/**
	 * Metodo que nos permite mostrar el formulario para ingresar un nuevo Employee
	 * donde por medio del controller mostramos el template new-employee.html
	 * @param model Parametro que se usa para agregar informacion al template,
	 * @return retorna el template nuevoempleado.html
	 */
	
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
	/**
	 * Metodo que sirve para capturar los valores o informacion ingresada en el template
	 * nuevoempleado.html por medio del metodo GET, para mandarla y almacenarla a la base
	 * de datos y ademas crear el objeto usuario para para employee.
	 * @param emp parametro Modelo que captura lo ingresado en la vista.
	 * @param result parametro que caputra si existe algun error en la vista.
	 * Atributos de la clase employee que sirven para asignar los valores manualmente. 
	 * @param employeeNumber
	 * @param lastName
	 * @param firstName
	 * @param extension
	 * @param email
	 * @param officeCode
	 * @param reportsTo
	 * @param jobTitle
	 * Atributos de la clase Usuario que sirven para asignar los valores manualmente. 
	 * @param id
	 * @param usuario
	 * @param password
	 * @param role
	 * @param model parametro que caputra los valores ingresados en la vista para devolver 
	 * los valores cargados en caso de que ocurra un validacion.
	 * @return retorna la vista donde se almacenan todos los Employee (listarempleados.html) o 
	 * si se presenta algun error de validacion en la vista nos muestra nuevamente la vista
	 * nuevoempleado.html
	 */
	
	@PostMapping("/employee/save")
	public String saveEmployeePage(@Valid @ModelAttribute("employee") Employee emp, BindingResult result, @RequestParam(name="employeeNumber") String employeeNumber, @RequestParam(name="lastName") String lastName,
			@RequestParam(name="firstName") String firstName, @RequestParam(name="extension") String extension, @RequestParam(name="email") String email,
			@RequestParam(name="jobTitle") String jobTitle, @RequestParam(name="employee.employeeNumber") String employee, @RequestParam (name="user.id") String id, @RequestParam (name="user.username") String username,
			@RequestParam (name="user.password") String password,@RequestParam (name="user.role") String role, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/save invoke the post method");
		
		if(result.hasErrors()) {
			model.addAttribute("employee", emp);
			model.addAttribute("employees", employeeService.getAllEmployees());
			model.addAttribute("offices", officeService.getAllOffices());
			LOGGER.info("RESULT : Page is displayed nuevoempleado.html");
			return "nuevoempleado";
		}else {
			if(emp.getOfficeCode()!=null) {
				Employee employe = new Employee();
				//User user = new User();
				//employe = this.employeeService.getEmployee();
				employe.setEmployeeNumber(employeeNumber);
				employe.setLastName(lastName);
				employe.setFirstName(firstName);
				employe.setExtension(extension);
				employe.setEmail(email);
				employe.setOfficeCode(emp.getOfficeCode());
				employe.setJobTitle(jobTitle);		
				if(Integer.valueOf(employee)==0) {
					employe.setEmployee(null);
				}else {
					employe.setEmployee(this.employeeService.getEmployeeById(Integer.valueOf(employee)));
				}

				employe.setUser(emp.getUser());
				LOGGER.info("METHOD : saveEmployeePage() -- PARAMS: employee'"+employe+"'");
				try {
					employeeService.saveEmployee(employe);
					attribute.addFlashAttribute("success", "The changes were saved successfully!");
				} catch (Exception e) {
					LOGGER.info("EXCEPTION INFO: '"+e+"'");
					attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
				}
				LOGGER.info("RESULT : The page is redirected to /employee/list/");
				return "redirect:/employee/list/";
			}else {
				attribute.addFlashAttribute("info", "Info: There are no offices loaded in the system, you must load at least one!");
				LOGGER.info("RESULT : The page is redirected to /employee/new/");
				return "redirect:/employee/new/";
			}
		}
	}
	/**
	 * Modelo que muestra la vista donde esta la tabla de todos los employees, la vista se
	 * llama listarempleados.html
	 * @return retorna el modelo donde esta la vista listarempleados.html que muestra la lista 
	 * de todos los employees.
	 */
	
	@GetMapping("/employee/list")
	public String getListEmployeePage(Model model) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/list invoke the get method");
		LOGGER.info("METHOD : getListEmployeePage()");
		model.addAttribute("employees", employeeService.getAllEmployees());
		LOGGER.info("RESULT : Page is displayed listarempleados.html");
		return "listarempleados";
	}
	/**
	 * Modelo principalmente que sirve para editar informacion del objeto (Employee)
	 * mostrando la vista del formulario (nuevoempleado.html) con valores que ya tiene 
	 * en la base de datos para ser modificado a eleccion. 
	 * @param number parametro que nos permite identificar el objeto a editar por medio del id
	 * @return retorna la vista nuevoempleado.html
	 */
	
	@GetMapping("/employee/edit/{number}")
	public String getEditEmployeePage(@PathVariable("number") int number, Model model, RedirectAttributes attribute) {
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
	
	/**
	 * Metodo que permite eliminar un objeto de la vista (listarempleados.html)
	 * atraves del id del objeto.
	 * @param number parametro que permite identificar el objeto a eliminar
	 * @return retorna la vista listarempleados.html
	 */
	
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
	/**
	 * Metodo que permite confirmar el objeto a eliminar de la vista (listarempleados.html)
	 * atraves del id del objeto.
	 * @param number parametro que permite identificar el objeto a eliminar
	 * @return retorna la vista listarempleados.html
	 */
	
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
		LOGGER.info("RESULT : the page is redirected to /employee/list/");
		return "redirect:/employee/list/";
	}
	
	/**
	 * Metodo que permite buscar un objeto de la vista (listarempleados.html)
	 * atraves del id del objeto.
	 * @param var parametro que permite identificar el objeto a eliminar
	 * @return retorna la vista listarempleados.html
	 */
	@GetMapping("/employee/search")
	public String getSearchEmployeePage(@RequestParam(name="var") String var, Model model) {
		LOGGER.info("CONTROLLER : EmployeeController with /employee/search invoke the get method");
		LOGGER.info("METHOD : getSearchEmployeePage()");
		LOGGER.info("RESULT : Page is displayed listarempleados.html");
		model.addAttribute("employees", employeeService.getEmployees(var));
		return "listarempleados";
	}
	
	
}
