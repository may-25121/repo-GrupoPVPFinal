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

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;


/**
 * Clase CustomerController
 * Clase que responde a los eventos e invoca peticiones de Customers
 * y ademas es el intermediario entre la vista y el modelo .
 * @author Yapura- Bejarano
 *
 */
@Controller
public class CustomerController {
	
	private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	private  Customer customer;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	@Autowired
	@Qualifier("employeeServiceMysql")
	private IEmployeeService employeeService;
	
	
	/**
	 * Metodo que nos permite mostrar el formulario para ingresar un nuevo Customers
	 * donde por medio del controller mostramos el template new-customer.html
	 * @param model Parametro que se usa para agregar informacion al template,
	 * @return retorna el template nuevocliente.html
	 */
	@GetMapping("/customer/new")
	public String getNewCustomerPage(Model model) {
		LOGGER.info("CONTROLLER : CustomerController con /customer/new invoke the get method");
		LOGGER.info("METHOD : getNewCustomerPage()");
		model.addAttribute("customer", customerService.getCustomer());
		model.addAttribute("employees", employeeService.getAllEmployees());
		LOGGER.info("RESULT : Page is displayed nuevocliente.html");
		return "nuevocliente";
	}
	
	/**
	 * Metodo que sirve para capturar los valores o informacion ingresada en el template
	 * nuevocustomer.html por medio del metodo POST, para mandarla y almacenarla a la base
	 * de datos.
	 * @param unCustomer parametro Modelo que captura lo ingresado en la vista.
	 * @param result parametro que caputra si existe algun error en la vista.
	 * @return retorna la vista donde se almacenan todos los clientes (listarclientes.html) o si 
	 * se presenta algun error de validacion en la vista nos muestra nuevamente la 
	 * vista nuevocustomer.html
	 */
	
	@PostMapping("/customer/save")
	public String saveCustomerPage(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, Model model
			,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/save invoke the post method");
		LOGGER.info("METHOD : saveCustomerPage() -- PARAMS: customer'"+customer+"'");
		if(result.hasErrors()) {
			model.addAttribute("customer", customer);
			model.addAttribute("employees", employeeService.getAllEmployees());
			LOGGER.info("RESULT : Page is displayed nuevocliente.html");
			return "nuevocliente";
		}else {
			if(customer.getSalesRepEmployeeNumber()!=null) {
				try {
					customerService.saveCustomer(customer);
					attribute.addFlashAttribute("success", "The changes were saved successfully!");
				} catch (Exception e) {
					LOGGER.info("EXCEPTION INFO: '"+e+"'");
					attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
				}
				LOGGER.info("RESULT: the page is redirected to /customer/list/");
				return "redirect:/customer/list/";	
			}else {
				attribute.addFlashAttribute("info", "Info: There are no employees loaded in the system, you must load at least one!");
				LOGGER.info("RESULT : The page is redirected to /customer/new/");
				return "redirect:/customer/new/";
			}
		}
	}
	
	/**
	 * Modelo que muestra la vista donde esta la tabla de todos los customers, la vista se
	 * llama listarclientes.html
	 * @return retorna el modelo donde esta la vista listarclientes.html que muestra la lista 
	 * de todos los clientes.
	 */
	
	@GetMapping("/customer/list")
	public String getListCustomerPage(Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/list invoke the get method");
		LOGGER.info("METHOD : getListCustomerPage()");
		model.addAttribute("customers", customerService.getAllCustomers());
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		return "listarclientes";
	}
	
	/**
	 * Modelo principalmente que sirve para editar informacion del objeto (Customer)
	 * mostrando la vista del formulario (nuevocliente.html) con valores que ya tiene 
	 * en la base de datos para ser modificado a eleccion. 
	 * @param number parametro que nos permite identificar el objeto a editar por medio del number
	 * @return retorna la vista nuevocliente.html
	 */
	
	@GetMapping("/customer/edit/{number}")
	public String getEditCustomerPage(@PathVariable("number") int number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/edit/{number} invoke the get method");
		LOGGER.info("METHOD : getEditCustomerPage()");
		if(!customerService.getCheckCustomerById(String.valueOf(number))) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /customer/list/");
			return "redirect:/customer/list/";
		}
		this.customer = customerService.getCustomerById(number);
		model.addAttribute("customer", this.customer);
		model.addAttribute("employees", employeeService.getAllEmployees());
		LOGGER.info("RESULT : Page is displayed nuevoempleado.html");
		return "nuevocliente";
	}
	
	/**
	 * Metodo que permite eliminar un objeto de la vista (listarclientes.html)
	 * atraves del number del objeto.
	 * @param number parametro que permite identificar el objeto a eliminar
	 * @return retorna la vista listarclientes.html
	 */
	
	@GetMapping("/customer/delete/{number}")
	public String getDeleteCustomerPage(@PathVariable("number")String number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/delete/{number} invoke the get method");
		LOGGER.info("METHOD : getDeleteCustomerPage()");
		if(!customerService.getCheckCustomerById(String.valueOf(number))) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /customer/list/");
			return "redirect:/customer/list/";
		}
		Customer customer = customerService.getCustomerById(Integer.valueOf(number));
		model.addAttribute("customer", customer);
		LOGGER.info("RESULT : Page is displayed eliminarcliente.html");
		return "eliminarcliente";
	}
	/**
	 * Metodo que permite confirmarar al borrar un objeto de la vista (listarclientes.html)
	 * atraves del number del objeto.
	 * @param number parametro que permite identificar el objeto a /delete/confirm
	 * @return retorna la vista listarclientes.html
	 */
	
	@GetMapping("/customer/delete/confirm/{number}")
	public String getConfirmDeleteCustomerPage(@PathVariable("number") String number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/delete/confirm/{number} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteCustomerPage()");
		try {
			customerService.deleteCustomerById(Integer.valueOf(number));
			attribute.addFlashAttribute("warning", "Record deleted successfully!");	
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
		}
		LOGGER.info("RESULT : the page is redirected to /customer/list/");
		return "redirect:/customer/list/";
	}
	
	/**
	 * Metodo que permite bucar  un objeto de la vista (listarclientes.html)
	 * atraves del var del objeto.
	 * @param var parametro que permite identificar el objeto a buscar
	 * @return retorna la vista listarclientes.html
	 */
	
	@GetMapping("/customer/search")
	public String getSearchCustomerPage(@RequestParam(name="var") String var, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/search invoke the get method");
		LOGGER.info("METHOD : getSearchCustomerPage()");
		model.addAttribute("customers", customerService.getCustomers(var));
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		return "listarclientes";
	}
	
}
