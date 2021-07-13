package ar.edu.unju.fi.tpfinal.controller;

import java.util.Date;

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

import ar.edu.unju.fi.tpfinal.model.Order;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IOrderService;

@Controller
public class OrdersController {
	
	private static final Log LOGGER = LogFactory.getLog(CustomerController.class);

	@Autowired
	private Order order;
	
	@Autowired
	@Qualifier("orderServiceMysql")
	private IOrderService orderService;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	@GetMapping("/order/new")
	public String getNewOrderPage(Model model) {
		LOGGER.info("CONTROLLER : OrdersController con /order/new invoke the get method");
		LOGGER.info("METHOD : getNewOrderPage()");
		model.addAttribute("order", orderService.getOrder());
		model.addAttribute("customers", customerService.getAllCustomers());
		LOGGER.info("RESULT : Page is displayed nuevaorden.html");
		return "nuevaorden";
	}
	
	@PostMapping("/order/save")
	public String saveOrderPage(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model,
			RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrdersController with /order/save invoke the post method");
		LOGGER.info("METHOD : saveOrderPage() -- PARAMS: order'"+order+"'");
		if(result.hasErrors()) {
			model.addAttribute("order", order);
			model.addAttribute("customers", customerService.getAllCustomers());
			LOGGER.info("RESULT : Page is displayed nuevaorden.html");
			return "nuevaorden";			
		}else {
			if(order.getCustomerNumber()!=null) {
				try {
					orderService.saveOrder(order);
					attribute.addFlashAttribute("success", "The changes were saved successfully!");
				} catch (Exception e) {
					LOGGER.info("EXCEPTION INFO: '"+e+"'");
					attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
				}
				LOGGER.info("RESULT : The page is redirected to /order/list/");
				return "redirect:/order/list/";

			}else {
				//model.addAttribute("order", order);
				//model.addAttribute("customers", customerService.getAllCustomers());
				attribute.addFlashAttribute("info", "Info: There are no clients loaded in the system, you must load at least one!");
				LOGGER.info("RESULT : The page is redirected to /order/new/");
				return "redirect:/order/new/";
			}
		}
	}
	
	@GetMapping("/order/list")
	public String getListOrderPage(Model model) {
		LOGGER.info("CONTROLLER : OrdersController with /order/list invoke the get method");
		LOGGER.info("METHOD : getListOrderPage()");
		model.addAttribute("orders", orderService.getAllOrders());
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		return "listarordenes";
	}
	
	@GetMapping("/order/edit/{number}")
	public String getEditOrderPage(@PathVariable("number") String number, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrdersController with /customer/edit/{number} invoke the get method");
		LOGGER.info("METHOD : getEditOrderPage()");
		if(!orderService.getCheckOrderById(number)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /employee/list/");
			return "redirect:/order/list/";				
		}
		this.order = orderService.getOrderById(Integer.valueOf(number));
		model.addAttribute("order", this.order);
		model.addAttribute("customers", customerService.getAllCustomers());
		LOGGER.info("RESULT : Page is displayed nuevocliente.html");
		return "nuevaorden";
	}
	
	@GetMapping("/order/delete/{number}")
	public String getDeleteOrderPage(@PathVariable("number") String number, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrderController with /order/delete/{number} invoke the get method");
		LOGGER.info("METHOD : getDeleteOrderPage()");
		if(!orderService.getCheckOrderById(number)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /employee/list/");
			return "redirect:/order/list/";				
		}
		Order order = orderService.getOrderById(Integer.valueOf(number));
		model.addAttribute("order", order);
		LOGGER.info("RESULT : Page is displayed eliminarorden.html");
		return "eliminarorden";
	}
	
	@GetMapping("/order/delete/confirm/{number}")
	public String getConfirmDeleteOrder(@PathVariable("number") String number, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrdersController with /order/delete/confirm/{number} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteOrder()");
		try {
			orderService.deleteOrderById(Integer.valueOf(number));
			attribute.addFlashAttribute("warning", "Record deleted successfully!");
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");		
		}
		LOGGER.info("RESULT : the page is redirected to /order/list/");
		return "redirect:/order/list/";
	}
	
	@GetMapping("/order/search")
	public String getSearchOrderPage(@RequestParam(name= "var") String var, Model model) {
		LOGGER.info("CONTROLLER : OrdersController with /customer/search invoke the get method");
		LOGGER.info("METHOD : getSearchOrderPage()");
		LOGGER.info("RESULT : Page is displayed listarordenes.html");
		model.addAttribute("orders", orderService.getOrders(var));
		return "listarordenes";
	}
	
}
