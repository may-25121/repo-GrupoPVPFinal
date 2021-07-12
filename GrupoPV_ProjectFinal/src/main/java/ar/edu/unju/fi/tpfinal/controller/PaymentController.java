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
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.model.PaymentsId;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IPaymentService;

@Controller
public class PaymentController {
	
	//private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	@Qualifier("paymentServiceMysql")
	private IPaymentService paymentService;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	@GetMapping("/payment/nuevo")
	public String getNuevoPaymentPage(Model model) {
		model.addAttribute("payment", paymentService.getPayment());
		model.addAttribute("customer", customerService.getAllCustomers());
		model.addAttribute("payments", paymentService.getAllPayment());
		return "nuevopago";
	}
	/*
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
	*/
	
	@PostMapping("/payment/guardar")
	public ModelAndView savePaymentPage(@Valid @ModelAttribute("payment") Payment payment, BindingResult result
			,@RequestParam(name="id.customerNumber") String customerNumber, @RequestParam(name="id.checkNumber")String checkNumber) {
		if(result.hasErrors()) {
			ModelAndView model = new ModelAndView("nuevopago");
			model.addObject(payment);
			model.addObject("customer",customerService.getAllCustomers());
			return model;
		} else {
			PaymentsId paymentsId = new PaymentsId(customerService.getCustomerById(Integer.valueOf(customerNumber)),checkNumber);
			ModelAndView modelView = new ModelAndView("listarpago");
			payment.setId(paymentsId);
			paymentService.savePayment(payment);
			modelView.addObject("payments", paymentService.getAllPayment());
			return modelView;
		}
	}
	
	@GetMapping("/payment/lista")
	public String getListPaymentPage(Model model) {
		model.addAttribute("payments", paymentService.getAllPayment());
		return "listarpagos";
	}
	/*
	@GetMapping("/payment/edit/{id}")
	public ModelAndView editPaymentPage(@PathVariable(value="id") String id) {
		ModelAndView modelView = new ModelAndView("nuevopago");
		Payment payment = paymentService.getPaymentById(id);
		modelView.addObject("payment", payment);
		modelView.addObject("customer", customerService.getAllCustomers());
		return modelView;
	}
	
	@GetMapping("/payment/borrar/{id}")
	public ModelAndView deletePaymentPage(@PathVariable(value="id") String id) {
		ModelAndView modelView = new ModelAndView("eliminarpago");
		paymentService.deletePaymentById(id);
		return modelView;
	}
	
	@GetMapping("/payment/borrar/confirmar/{id}")
	public ModelAndView getConfirmarBorrarPaymentPage(@PathVariable(value="id") String id) {
		ModelAndView modelView = new ModelAndView("listarpagos");
		paymentService.deletePaymentById(id);
		modelView.addObject("payment", paymentService.getAllPayment());
		return modelView;
	}*/
	
}
