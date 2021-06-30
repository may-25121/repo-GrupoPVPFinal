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

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IEmployeeService;


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
	
	@GetMapping("/customer/new")
	public String getNewCustomerPage(Model model) {
		LOGGER.info("CONTROLLER : CustomerController con /customer/new invoke the get method");
		LOGGER.info("METHOD : getNewCustomerPage()");
		LOGGER.info("RESULT : Page is displayed nuevocliente.html");
		model.addAttribute("customer", customerService.getCustomer());
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "nuevocliente";
	}
	
	@PostMapping("/customer/save")
	public String saveCustomerPage(@ModelAttribute("customer") Customer customer, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/save invoke the post method");
		LOGGER.info("METHOD : saveCustomerPage() -- PARAMS: customer'"+customer+"'");
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		try {
				customerService.saveCustomer(customer);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("customers", customerService.getAllCustomers());
		return "listarclientes";
	}
	
	@GetMapping("/customer/list")
	public String getListCustomerPage(Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/list invoke the get method");
		LOGGER.info("METHOD : getListCustomerPage()");
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		model.addAttribute("customers", customerService.getAllCustomers());
		return "listarclientes";
	}
	
	@GetMapping("/customer/edit/{number}")
	public String getEditCustomerPage(@PathVariable("number") int number, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/edit/{number} invoke the get method");
		LOGGER.info("METHOD : getEditCustomerPage()");
		LOGGER.info("RESULT : Page is displayed nuevoempleado.html");
		this.customer = customerService.getCustomerById(number);
		model.addAttribute("customer", this.customer);
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "nuevocliente";
	}
	
	@GetMapping("/customer/delete/{number}")
	public String getDeleteCustomerPage(@PathVariable("number")String number, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/delete/{number} invoke the get method");
		LOGGER.info("METHOD : getDeleteCustomerPage()");
		LOGGER.info("RESULT : Page is displayed eliminarcliente.html");
		Customer customer = customerService.getCustomerById(Integer.valueOf(number));
		model.addAttribute("customer", customer);
		return "eliminarcliente";
	}
	
	@GetMapping("/customer/delete/confirm/{number}")
	public String getConfirmDeleteCustomerPage(@PathVariable("number") String number, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/delete/confirm/{number} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteCustomerPage()");
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		customerService.deleteCustomerById(Integer.valueOf(number));
		model.addAttribute("customers", customerService.getAllCustomers());
		return "listarclientes";
	}
	
	@GetMapping("/customer/search")
	public String getSearchCustomerPage(@RequestParam(name="customernumber") String customernumber, @RequestParam(name="city") String city, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/search invoke the get method");
		LOGGER.info("METHOD : getSearchCustomerPage()");
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		model.addAttribute("customers", customerService.getCustomers(customernumber, city));
		return "listarclientes";
	}
	
}
