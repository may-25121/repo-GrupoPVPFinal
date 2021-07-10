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
	
	@GetMapping("/product/new")
	public String getProductPage (Model model) {
		LOGGER.info("CONTROLLER : ProductsController con /product/new invoke the get method");
		LOGGER.info("METHOD : getProductPage()");
		LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
		model.addAttribute("products", productsService.getProducts());
		model.addAttribute("productlines", productlinesService.getAllProductlines());
		return "nuevoproducto";
	}
	
	@PostMapping("/product/save")
	public String saveProductPage(@Valid @ModelAttribute("products")Products products,BindingResult result,  Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/save invoke the post method");
		LOGGER.info("METHOD : saveProductPage() -- PARAMS: customer'"+products+"'");
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

	@GetMapping("/product/list")
	public String getListProductPage(Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/list invoke the get method");
		LOGGER.info("METHOD : getListProductPage()");
		LOGGER.info("RESULT : Page is displayed listproductos.html");
		model.addAttribute("products", productsService.getAllProducts());
		return "listarproductos";
		
	}
	
	@GetMapping("/product/edit/{code}")
	public String getEditProductPage(@PathVariable ("code") int code,Model model){
		LOGGER.info("CONTROLLER : ProductsController with /product/edit/{code} invoke the get method");
		LOGGER.info("METHOD : getEditProductPage(@PathVariable()");
		LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
		this.products = productsService.getProductsById(String.valueOf(code));
		model.addAttribute("products", this.products);
		model.addAttribute("productlines", productlinesService.getAllProductlines());
		return"nuevoproducto";
	}

	@GetMapping("/product/delete/{code}")
	public String getDeleteProductPage(@PathVariable ("code") String code , Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/delete/{code} invoke the get method");
		LOGGER.info("METHOD : getDeleteProductPage()");
		LOGGER.info("RESULT : Page is displayed eliminarproducto.html");
		Products product = productsService.getProductsById(code);
		model.addAttribute("products", product);
		return "eliminarproducto";
	}

	@GetMapping("/product/delete/confirm/{code}")
	public String getConfirmDeleteProductPage(@PathVariable("code") String code, Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/delete/confirm/{code} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteProductPage()");
		LOGGER.info("RESULT : Page is displayed listarproductos.html");
		productsService.deleteProductsById(code);
		model.addAttribute("products", productsService.getAllProducts());
		return "listarproductos";
	}

	@GetMapping("/product/search")
	public String getSearchProductPage(@RequestParam(name="var") String var,  Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/search invoke the get method");
		LOGGER.info("METHOD : getSearchProductPage()");
		LOGGER.info("RESULT : Page is displayed listarproductos.html");
		model.addAttribute("products", products);
		model.addAttribute("products", productsService.getProducts(var));
		return "listarproductos";
	}

}
