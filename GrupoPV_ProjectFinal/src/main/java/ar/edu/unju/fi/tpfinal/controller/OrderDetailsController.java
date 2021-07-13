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

/**
 * Clase OrderDetailController
 * Clase que responde a los eventos e invoca peticiones de Office
 * y ademas es el intermediario entre la vista y el modelo .
 * @author Yapura-Bejarano
 *
 */
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
	
	/**
	 * Metodo que nos permite mostrar el formulario para ingresar una nueva OrderDetail
	 * donde por medio del controller mostramos el template nuevodetalleorden.html
	 * @param model Parametro que se usa para agregar informacion al template,
	 * @return retorna el template nuevodetalleorden.html
	 */
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
	
	/**
	 * Metodo que sirve para capturar los valores o informacion ingresada en el template
	 * nuevodetalleorden.html por medio del metodo POST, para mandarla y almacenarla a la base
	 * de datos y ademas ingresa valores a los atributos a la clase OrderDetailId para que se
	 * genere la tabla que relaciona Products con OrderDetail en la base de datos.
	 * @param orderDetail
	 * @param result
	 * @param model
	 * @return retorna la vista (all-orderdetail) donde se muestran todas las OrderDetails que estan en la
	 * base de datos o si presenta alguna error de validacion nos muetra nuevamente la vista 
	 * nuevadetalleorden.html
	 */
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
	
	
	/**
	 * Modelo que muestra la vista donde esta la tabla de todas las OrderDetail, la vista se
	 * llama listardetalleordenes.html
	 * @return retorna el modelo donde esta la vista listardetalleordenes.html que muestra la lista 
	 * de todas las OrderDetail.
	 */
	@GetMapping("/orderdetail/list")
	public String getListOrderDetailPage(Model model) {
		LOGGER.info("CONTROLLER :  OrderDetailsController with /orderdetail/list invoke the get method");
		LOGGER.info("METHOD : getListOrderDetailPage()");
		model.addAttribute("orderDetails", orderDetailService.getAllOrderDetails());
		LOGGER.info("RESULT : Page is displayed listardetalleordenes.html");
		return "listardetalleordenes";
	}
	
	
	/**
	 * Modelo principalmente que sirve para editar informacion del objeto (OrderDetail)
	 * mostrando la vista del formulario (nuevadetalleorden.html) con valores que ya tiene 
	 * en la base de datos para ser modificado a eleccion. 
	 * @param productCode parametro que sirve para identificar el product en la order.
	 * @param orderNumber parametro que relaciona la order con el product.
	 * @return retorna la vista nuevadetalleorden.html
	 */
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
	
	/**
	 * Modelo principalmente que sirve para eliminar un objeto (OrderDetail) de
	 * la vista listardetalleordenes.html
	 * @param productCode parametro que sirve para identificar el product en la order.
	 * @param orderNumber parametro que relaciona la order con el product.
	 * @return retorna la vista listardetalleordenes.html
	 */
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
	
	/**
	 * Modelo principalmente que sirve para confirmar antes de eliminar un objeto (OrderDetail) de
	 * la vista listardetalleordenes.html
	 * @param productCode parametro que sirve para identificar el product en la order.
	 * @param orderNumber parametro que relaciona la order con el product.
	 * @return retorna la vista listardetalleordenes.html
	 */
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
	
	/**
	 * Modelo principalmente que sirve para buscar un objeto (OrderDetail) de
	 * la vista listardetalleordenes.html
	 * @param var parametro que sirve para identificar el var a buscar
	 * @return retorna la vista listardetalleordenes.html
	 */
	@GetMapping("/orderdetail/search")
	public String getSearchOrderDetailPage(@RequestParam(name="var")String var, Model model) {
		LOGGER.info("CONTROLLER : OrderDetailsController with /orderdetail/search invoke the get method");
		LOGGER.info("METHOD : getSearchOrderDetailPage()");
		model.addAttribute("orderDetails", orderDetailService.getOrderDetails(var));
		LOGGER.info("RESULT : Page is displayed listardetalleordenes.html");
		return "listardetalleordenes";
	}

}
