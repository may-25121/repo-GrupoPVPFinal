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
		model.addAttribute("customer", customerService.getCustomer());
		model.addAttribute("employees", employeeService.getAllEmployees());
		LOGGER.info("RESULT : Page is displayed nuevocliente.html");
		return "nuevocliente";
	}
	
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
	
	@GetMapping("/customer/list")
	public String getListCustomerPage(Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/list invoke the get method");
		LOGGER.info("METHOD : getListCustomerPage()");
		model.addAttribute("customers", customerService.getAllCustomers());
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		return "listarclientes";
	}
	
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
	
	@GetMapping("/customer/search")
	public String getSearchCustomerPage(@RequestParam(name="var") String var, Model model) {
		LOGGER.info("CONTROLLER : CustomerController with /customer/search invoke the get method");
		LOGGER.info("METHOD : getSearchCustomerPage()");
		model.addAttribute("customers", customerService.getCustomers(var));
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		return "listarclientes";
	}
	
}
