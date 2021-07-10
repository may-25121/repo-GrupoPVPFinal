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
	public String saveOrderDetailPage(@ModelAttribute("orderDetail") OrderDetails orderDetail, Model model, 
		@RequestParam(name="id.orderNumber") String orderNumber, @RequestParam(name="id.productCode")String productCode) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/save invoke the post method");
		OrderDetailsId orderDetailsId= new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		//orderDetailsId.setOrderNumber(orderService.getOrderById(Integer.valueOf(orderNumber)));
		//orderDetailsId.setProductCode(productService.getProductsById(productCode)); 
		orderDetail.setId(orderDetailsId);
		LOGGER.info("METHOD : saveOrderDetailPage() -- PARAMS: orderDetail'"+orderDetail+"'");
		orderDetailService.saveOrderDetail(orderDetail);
		model.addAttribute("orderDetails", orderDetailService.getAllOrderDetails());
		LOGGER.info("RESULT : Page is displayed listardetalleordenes.html");
		return "listardetalleordenes";
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
	public String getEditOrderDetailPage(@PathVariable("id.orderNumber") String orderNumber,@PathVariable("id.productCode") String productCode, Model model) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/edit/{id.orderNumber}/{id.productCode} invoke the get method");
		LOGGER.info("METHOD : getEditOrderDetailPage()");
		OrderDetailsId orderDetailsId = new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		model.addAttribute("orderDetail", orderDetailService.getOrderDetailById(orderDetailsId));
		model.addAttribute("orders", orderService.getAllOrders());
		model.addAttribute("products", productService.getAllProducts());
		LOGGER.info("RESULT : Page is displayed nuevodetalleorden.html");
		return "nuevodetalleorden";
	}
	
	@GetMapping("/orderdetail/delete/{id.orderNumber}/{id.productCode}")
	public String getDeleteOrderDetailPage(@PathVariable("id.orderNumber") String orderNumber,@PathVariable("id.productCode") String productCode, Model model) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/delete/{id.orderNumber}/{id.productCode} invoke the get method");
		LOGGER.info("METHOD : getDeleteOrderDetailPage()");
		OrderDetailsId orderDetailsId = new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		model.addAttribute("orderDetail", orderDetailService.getOrderDetailById(orderDetailsId));
		LOGGER.info("RESULT : Page is displayed eliminardetalleorden.html");
		return "eliminardetalleorden";
	}
	
	@GetMapping("/orderdetail/delete/confirm/{id.orderNumber}/{id.productCode}")
	public String getConfirmDeleteOrderDetailPage(@PathVariable("id.orderNumber") String orderNumber,@PathVariable("id.productCode") String productCode, Model model) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/delete/confirm/{id.orderNumber}/{id.productCode} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteOrderDetailPage()");
		OrderDetailsId orderDetailsId = new OrderDetailsId(productService.getProductsById(productCode),orderService.getOrderById(Integer.valueOf(orderNumber)));
		orderDetailService.deleteOrderDetailById(orderDetailsId);
		model.addAttribute("orderDetails", orderDetailService.getAllOrderDetails());
		LOGGER.info("RESULT : Page is displayed listardetalleordenes.html");
		return "listardetalleordenes";
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
