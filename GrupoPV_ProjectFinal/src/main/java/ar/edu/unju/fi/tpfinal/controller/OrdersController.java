package ar.edu.unju.fi.tpfinal.controller;

import java.util.Date;

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
		LOGGER.info("RESULT : Page is displayed nuevaorden.html");
		model.addAttribute("order", orderService.getOrder());
		model.addAttribute("customers", customerService.getAllCustomers());
		return "nuevaorden";
	}
	
	@PostMapping("/order/save")
	public String saveOrderPage(@ModelAttribute("order") Order order, Model model) {
		LOGGER.info("CONTROLLER : OrdersController with /order/save invoke the post method");
		LOGGER.info("METHOD : saveOrderPage() -- PARAMS: order'"+order+"'");
		LOGGER.info("RESULT : Page is displayed listarordenes.html");
		try {
			orderService.saveOrder(order);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("orders", orderService.getAllOrders());
		return "listarordenes";
	}
	
	@GetMapping("/order/list")
	public String getListOrderPage(Model model) {
		LOGGER.info("CONTROLLER : OrdersController with /order/list invoke the get method");
		LOGGER.info("METHOD : getListOrderPage()");
		LOGGER.info("RESULT : Page is displayed listarclientes.html");
		model.addAttribute("orders", orderService.getAllOrders());		
		return "listarordenes";
	}
	
	@GetMapping("/order/edit/{number}")
	public String getEditOrderPage(@PathVariable("number") String number, Model model) {
		LOGGER.info("CONTROLLER : OrdersController with /customer/edit/{number} invoke the get method");
		LOGGER.info("METHOD : getEditOrderPage()");
		LOGGER.info("RESULT : Page is displayed nuevocliente.html");
		this.order = orderService.getOrderById(Integer.valueOf(number));
		model.addAttribute("order", this.order);
		model.addAttribute("customers", customerService.getAllCustomers());
		return "nuevaorden";
	}
	
	@GetMapping("/order/delete/{number}")
	public String getDeleteOrderPage(@PathVariable("number") String number, Model model) {
		LOGGER.info("CONTROLLER : OrderController with /order/delete/{number} invoke the get method");
		LOGGER.info("METHOD : getDeleteOrderPage()");
		LOGGER.info("RESULT : Page is displayed eliminarorden.html");
		Order order = orderService.getOrderById(Integer.valueOf(number));
		model.addAttribute("order", order);
		return "eliminarorden";
	}
	
	@GetMapping("/order/delete/confirm/{number}")
	public String getConfirmDeleteOrder(@PathVariable("number") String number, Model model) {
		LOGGER.info("CONTROLLER : OrdersController with /order/delete/confirm/{number} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteOrder()");
		LOGGER.info("RESULT : Page is displayed listarordenes.html");
		orderService.deleteOrderById(Integer.valueOf(number));
		model.addAttribute("orders", orderService.getAllOrders());
		return "listarordenes";
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
