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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.model.Payment;
import ar.edu.unju.fi.tpfinal.model.PaymentsId;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;
import ar.edu.unju.fi.tpfinal.service.IPaymentService;

@Controller
public class PaymentController {
	
	private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	@Qualifier("paymentServiceMysql")
	private IPaymentService paymentService;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	@GetMapping("/payment/new")
	public String getNewPaymentPage(Model model) {
		LOGGER.info("CONTROLLER : PaymentController with /payment/new invoke the get method");
		LOGGER.info("METHOD : getNewPaymentPage()");
		model.addAttribute("payment", paymentService.getPayment());
		model.addAttribute("customer", customerService.getAllCustomers());
		//model.addAttribute("payments", paymentService.getAllPayment());
		LOGGER.info("RESULT : Page is displayed nuevopago.html");
		return "nuevopago";
	}
	
	@PostMapping("/payment/save")
	public String savePaymentPage(@Valid @ModelAttribute("payment") Payment payment, BindingResult result,Model model, RedirectAttributes attribute) {
		//	,@RequestParam(name="id.customerNumber") String customerNumber, @RequestParam(name="id.checkNumber")String checkNumber) {
		LOGGER.info("CONTROLLER : PaymentController with /payment/save invoke the post method");
		LOGGER.info("METHOD : savePaymentPage() -- PARAMS: payment'"+payment+"'");
		if(result.hasErrors()) {
			model.addAttribute("payment", payment);
			model.addAttribute("customer", customerService.getAllCustomers());
			return "nuevopago";
		} else {
			if(!payment.getId().getCheckNumber().isEmpty() && payment.getId().getCustomerNumber()!=null ) {
				try {
					paymentService.savePayment(payment);
					attribute.addFlashAttribute("success", "The changes were saved successfully!");
				} catch (Exception e) {
					LOGGER.info("EXCEPTION INFO: '"+e+"'");
					attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
				}
				LOGGER.info("RESULT : the page is redirected to /payment/list/");
				return "redirect:/payment/list/";	
			}else {
				attribute.addFlashAttribute("info", "Info: There are no customer products loaded in the system and/or check number, you must load at least one of each!");
				LOGGER.info("RESULT : The page is redirected to /payment/new/");
				return "redirect:/payment/new/";
			}
		}
	}
	
	@GetMapping("/payment/list")
	public String getListPaymentPage(Model model) {
		LOGGER.info("CONTROLLER :  PaymentController with /payment/list invoke the get method");
		LOGGER.info("METHOD : getListPaymentPage()");
		model.addAttribute("payments", paymentService.getAllPayment());
		LOGGER.info("RESULT : Page is displayed listarpagos.html");
		return "listarpagos";
	}

	@GetMapping("/payment/edit/{id.customerNumber}/{id.checkNumber}")
	public String editPaymentPage(@PathVariable("id.customerNumber") String customerNumber,
			@PathVariable("id.checkNumber") String checkNumber, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : PaymentController with /payment/edit/{customerNumber}/{checkNumber} invoke the get method");
		LOGGER.info("METHOD : editPaymentPage()");		
		PaymentsId paymentsId = new PaymentsId(customerService.getCustomerById(Integer.valueOf(customerNumber)),checkNumber);
		if(!paymentService.getCheckPaymentById(paymentsId)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /payment/list/");
			return "redirect:/payment/list/";			
		}
		model.addAttribute("payment", paymentService.getPaymentById(paymentsId));
		model.addAttribute("customer", customerService.getAllCustomers());
		LOGGER.info("RESULT : Page is displayed nuevopago.html");
		return "nuevopago";
	}
	
	@GetMapping("/payment/delete/{id.customerNumber}/{id.checkNumber}")
	public String deletePaymentPage(@PathVariable("id.customerNumber") String customerNumber,
			@PathVariable("id.checkNumber") String checkNumber, Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : PaymentController with /payment/delete/{customerNumber}/{checkNumber} invoke the get method");
		LOGGER.info("METHOD : deletePaymentPage()");
		PaymentsId paymentsId = new PaymentsId(customerService.getCustomerById(Integer.valueOf(customerNumber)),checkNumber);
		if(!paymentService.getCheckPaymentById(paymentsId)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /payment/list/");
			return "redirect:/payment/list/";			
		}
		model.addAttribute("payment", paymentService.getPaymentById(paymentsId));
		LOGGER.info("RESULT : Page is displayed eliminarpago.html");
		return "eliminarpago";
	}
	
	
	@GetMapping("/payment/delete/confirm/{id.customerNumber}/{id.checkNumber}")
	public String getConfirmDeletePaymentPage(@PathVariable("id.customerNumber") String customerNumber,@PathVariable("id.checkNumber") String checkNumber, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : PaymentController with /payment/delete/confirm/{customerNumber}/{checkNumber} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeletePaymentPage()");
		PaymentsId paimentId = new PaymentsId(customerService.getCustomerById(Integer.valueOf(customerNumber)),checkNumber);
		try {
			paymentService.deletePaymentById(paimentId);
			attribute.addFlashAttribute("warning", "Record deleted successfully!");
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
		}
		LOGGER.info("RESULT : the page is redirected to /payment/list/");
		return "redirect:/payment/list/";
	}
	
}
