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

import ar.edu.unju.fi.tpfinal.model.Products;
import ar.edu.unju.fi.tpfinal.service.IProductlinesService;
import ar.edu.unju.fi.tpfinal.service.IProductsService;
@Controller
public class ProductsController {
	private static final Log LOGGER = LogFactory.getLog(ProductsController.class);
	@Autowired
	 private Products products;
	
	
	@Autowired 
	@Qualifier("productsServiceMysql")
	private IProductsService productsService;
	@Autowired
	@Qualifier("productlinesServiceMysql")
	private IProductlinesService productlinesService;
	
	@GetMapping("/producto/nuevo")
	public String getProductoPage (Model model) {
		LOGGER.info("CONTROLLER : ProductsController con /producto/nuevo invoke the get method");
		LOGGER.info("METHOD : getProductoPage()");
		LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
	model.addAttribute("products", productsService.getProducts());
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	return "nuevoproducto";
	}
	@PostMapping("/producto/guardar")
	public String saveProductoPage(@Valid @ModelAttribute("products")Products products,BindingResult result,  Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /peoducto/guardar invoke the post method");
		LOGGER.info("METHOD : saveProductoPage() -- PARAMS: customer'"+products+"'");
		LOGGER.info("RESULT : Page is displayed listarproductos.html");
		if(result.hasErrors()) {
			model.addAttribute("products", products);
			return "nuevoproducto";
		}else {
		try {
			productsService.saveProducs(products);
		}catch (Exception e) {
				System.out.println(e.getMessage());
		}	
		model.addAttribute("products", productsService.getAllProducts());
		return "listarproductos";
	}
	
	}

	@GetMapping("/producto/listar")
	public String getListarProductoPage(Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /producto/listar invoke the get method");
		LOGGER.info("METHOD : getListarProductoPage()");
		LOGGER.info("RESULT : Page is displayed listproductos.html");
		model.addAttribute("products", productsService.getAllProducts());
		return "listarproductos";
		
	}
	
@GetMapping("/producto/editar/{code}")
public String getEditarPage(@PathVariable ("code") int code,Model model){
	LOGGER.info("CONTROLLER : ProductsController with /producto/editar/{code} invoke the get method");
	LOGGER.info("METHOD : getEditarPage(@PathVariable()");
	LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
this.products = productsService.getProductsById(code);
model.addAttribute("products", this.products);
model.addAttribute("productlines", productlinesService.getAllProductlines());
return"nuevoproducto";
}

@GetMapping("/producto/borrar/{Code}")
public String getBorrarPage(@PathVariable ("code") String code , Model model) {
	LOGGER.info("CONTROLLER : ProductsController with /producto/borrar/{code} invoke the get method");
	LOGGER.info("METHOD : getBorrarPage()");
	LOGGER.info("RESULT : Page is displayed eliminarproducto.html");
	Products products = productsService.getProductsById(Integer.valueOf(code));
model.addAttribute("products", products);
return "eliminarproducto";

}

@GetMapping("/producto/borrar/confirmar/{code}")
public String getConfirmarBorrarPage(@PathVariable("code") String code, Model model) {
	LOGGER.info("CONTROLLER : ProductsController with /producto/borrar/confirmar/{code} invoke the get method");
	LOGGER.info("METHOD : getConfirmarBorrarPage()");
	LOGGER.info("RESULT : Page is displayed listarproductos.html");
	productsService.deleteProductsById(Integer.valueOf(code));
	model.addAttribute("products", productsService.getAllProducts());
	return "listarproductos";
	
}

@GetMapping("/producto/buscar")
public String getBuscarProductoPage(@RequestParam(name="productcode") String productcode,  Model model) {
	LOGGER.info("CONTROLLER : ProductsController with /producto/buscarinvoke the get method");
	LOGGER.info("METHOD : getBuscarProductoPage()");
	LOGGER.info("RESULT : Page is displayed listarproductos.html");
	model.addAttribute("products", products);
	model.addAttribute("products", productsService.getProducts(productcode));
	return "listarproductos";

}

}
