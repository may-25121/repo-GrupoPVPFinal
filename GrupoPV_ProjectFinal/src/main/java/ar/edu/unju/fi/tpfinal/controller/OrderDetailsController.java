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

import ar.edu.unju.fi.tpfinal.model.OrderDetails;
import ar.edu.unju.fi.tpfinal.model.OrderDetailsId;
import ar.edu.unju.fi.tpfinal.service.IOrderDetailsService;
import ar.edu.unju.fi.tpfinal.service.IOrderService;
import ar.edu.unju.fi.tpfinal.service.IProductsService;

@Controller
public class OrderDetailsController {
	
	private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	@Qualifier("orderDetailServiceMysql")
	IOrderDetailsService orderDetailService;
	
	@Autowired
	@Qualifier("orderServiceMysql")
	IOrderService orderService;
	
	@Autowired
	@Qualifier("productsServiceMysql")
	IProductsService productService;
	
	@GetMapping("/orderdetail/new")
	public String getNewOrderDetailPage(Model model) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/ new invoke the get method");
		LOGGER.info("METHOD : getNewOrderDetailPage()");
		model.addAttribute("orders", orderService.getAllOrders());
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("orderDetail", orderDetailService.getorderDetail());
		LOGGER.info("RESULT : Page is displayed nuevodetalleorden.html");
		return "nuevodetalleorden";
	}
	
	@PostMapping("/orderdetail/save")
	public String saveOrderDetailPage(@Valid @ModelAttribute("orderDetail") OrderDetails orderDetail, BindingResult result, Model model,
		 RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/save invoke the post method");
		LOGGER.info("METHOD : saveOrderDetailPage() -- PARAMS: orderDetail'"+orderDetail+"'");
		if(result.hasErrors()) {
			model.addAttribute("orders", orderService.getAllOrders());
			model.addAttribute("products", productService.getAllProducts());
			model.addAttribute("orderDetail", orderDetail);
			LOGGER.info("RESULT : Page is displayed nuevodetalleorden.html");
			return "nuevodetalleorden";
		}else {
			//if(orderDetail.getId().getOrderNumber()!=null && orderDetail.getId().getProductCode()!=null) {
			if(orderDetail.getId()!=null) {
				try {
					LOGGER.info("METHOD : saveOrderDetailPage() -- PARAMS: orderDetail'"+orderDetail+"'");
					orderDetailService.saveOrderDetail(orderDetail);
					attribute.addFlashAttribute("success", "The changes were saved successfully!");
				} catch (Exception e) {
					LOGGER.info("EXCEPTION INFO: '"+e+"'");
					attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
				}
				LOGGER.info("RESULT : the page is redirected to /orderdetail/list/");
				return "redirect:/orderdetail/list/";
			}else {
				attribute.addFlashAttribute("info", "Info: There are no loaded products loaded in the system and there are no orders, you must load at least one of each!");
				LOGGER.info("RESULT : The page is redirected to /orderdetail/new/");
				return "redirect:/orderdetail/new/";
			}
		}
	}
	
	@GetMapping("/orderdetail/list")
	public String getListOrderDetailPage(Model model) {
		LOGGER.info("CONTROLLER :  OrderDetailsController with /orderdetail/list invoke the get method");
		LOGGER.info("METHOD : getListOrderDetailPage()");
		model.addAttribute("orderDetails", orderDetailService.getAllOrderDetails());
		LOGGER.info("RESULT : Page is displayed listardetalleordenes.html");
		return "listardetalleordenes";
	}
	
	@GetMapping("/orderdetail/edit/{id.orderNumber}/{id.productCode}")
	public String getEditOrderDetailPage(@PathVariable("id.orderNumber") String orderNumber,@PathVariable("id.productCode") String productCode, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/edit/{id.orderNumber}/{id.productCode} invoke the get method");
		LOGGER.info("METHOD : getEditOrderDetailPage()");
		OrderDetailsId orderDetailsId = new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		if(!orderDetailService.getCheckOrderDetailById(orderDetailsId)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /orderdetail/list/");
			return "redirect:/orderdetail/list/";
		}
		model.addAttribute("orderDetail", orderDetailService.getOrderDetailById(orderDetailsId));
		model.addAttribute("orders", orderService.getAllOrders());
		model.addAttribute("products", productService.getAllProducts());
		LOGGER.info("RESULT : Page is displayed nuevodetalleorden.html");
		return "nuevodetalleorden";
	}
	
	@GetMapping("/orderdetail/delete/{id.orderNumber}/{id.productCode}")
	public String getDeleteOrderDetailPage(@PathVariable("id.orderNumber") String orderNumber,@PathVariable("id.productCode") String productCode, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/delete/{id.orderNumber}/{id.productCode} invoke the get method");
		LOGGER.info("METHOD : getDeleteOrderDetailPage()");
		OrderDetailsId orderDetailsId = new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		if(!orderDetailService.getCheckOrderDetailById(orderDetailsId)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /orderdetail/list/");
			return "redirect:/orderdetail/list/";
		}
		model.addAttribute("orderDetail", orderDetailService.getOrderDetailById(orderDetailsId));
		LOGGER.info("RESULT : Page is displayed eliminardetalleorden.html");
		return "eliminardetalleorden";
	}
	
	@GetMapping("/orderdetail/delete/confirm/{id.orderNumber}/{id.productCode}")
	public String getConfirmDeleteOrderDetailPage(@PathVariable("id.orderNumber") String orderNumber,@PathVariable("id.productCode") String productCode, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/delete/confirm/{id.orderNumber}/{id.productCode} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteOrderDetailPage()");
		OrderDetailsId orderDetailsId = new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		try {
			orderDetailService.deleteOrderDetailById(orderDetailsId);
			attribute.addFlashAttribute("warning", "Record deleted successfully!");
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
		}
		LOGGER.info("RESULT : the page is redirected to /orderdetail/list/");
		return "redirect:/orderdetail/list/";
	}
	
	@GetMapping("/orderdetail/search")
	public String getSearchOrderDetailPage(@RequestParam(name="var")String var, Model model) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/search invoke the get method");
		LOGGER.info("METHOD : getSearchOrderDetailPage()");
		model.addAttribute("orderDetails", orderDetailService.getOrderDetails(var));
		LOGGER.info("RESULT : Page is displayed listardetalleordenes.html");
		return "listardetalleordenes";
	}

}
