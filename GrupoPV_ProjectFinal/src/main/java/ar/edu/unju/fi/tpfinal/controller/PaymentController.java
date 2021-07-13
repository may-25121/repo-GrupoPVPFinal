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

/**Clase PaymentsController
 * Clase que responde a los eventos e invoca peticiones de Payments
 * y ademas es el intermediario entre la vista y el modelo 
 * @author Yapura-Bejarano
 *
 */
@Controller
public class PaymentController {
	
	private static final Log LOGGER = LogFactory.getLog(CustomerController.class);
	
	@Autowired
	@Qualifier("paymentServiceMysql")
	private IPaymentService paymentService;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	/** metodo que permite mostrar e inicializar el formulario 
	 * @param model parametro utilizado para inyectar datos a la vista
	 * @return retorna el template nuevopago
	 */
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
	
	/**Metodo para guardar valores ingresados en el template nuevopago
	 * @param payment recibe valores de la vista
	 * @return retorna el template nuevopago
	 */
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
	
	/**metodo para mostrar el template all-paymentsy en el una coleccion de payments 
	 * @return retorna el template listarpagos
	 */
	@GetMapping("/payment/list")
	public String getListPaymentPage(Model model) {
		LOGGER.info("CONTROLLER :  PaymentController with /payment/list invoke the get method");
		LOGGER.info("METHOD : getListPaymentPage()");
		model.addAttribute("payments", paymentService.getAllPayment());
		LOGGER.info("RESULT : Page is displayed listarpagos.html");
		return "listarpagos";
	}

	/**metodo que sirve para editar Payment  mostrando el template nuevopago
	 * @param id.checkNumber parametro q permite identificar el objeto
	 * @param id.customerNumber parametro q permite identificar el objeto
	 * @return retorna template nuevopago con los datos de un payment
	 */
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
	/**Metodo de eliminacion de un obj payment
	 *@param id.checkNumber parametro q permite identificar el objeto
	 * @param id.customerNumber parametro q permite identificar el objeto
	 * @return retorna la pagina listarpagos
	 */
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
	
	/**Metodo que permite confirmar antes de eliminar un obj payment
	 *@param id.checkNumber parametro q permite identificar el objeto
	 * @param id.customerNumber parametro q permite identificar el objeto
	 * @return retorna la pagina listarpagos
	 */
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
