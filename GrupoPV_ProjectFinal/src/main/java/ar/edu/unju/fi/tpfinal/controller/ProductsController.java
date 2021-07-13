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
		model.addAttribute("products", productsService.getProducts());
		model.addAttribute("productlines", productlinesService.getAllProductlines());
		LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
		return "nuevoproducto";
	}
	
	@PostMapping("/product/save")
	public String saveProductPage(@Valid @ModelAttribute("products")Products products,BindingResult result,  Model model
			,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : ProductsController with /product/save invoke the post method");
		LOGGER.info("METHOD : saveProductPage() -- PARAMS: product'"+products+"'");
		if(result.hasErrors()) {
			model.addAttribute("products", products);
			model.addAttribute("productlines", productlinesService.getAllProductlines());
			LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
			return "nuevoproducto";
		}else {
			if(products.getProductlines()!=null) {
				try {
					productsService.saveProducs(products);
					attribute.addFlashAttribute("success", "The changes were saved successfully!");
				}catch (Exception e) {
					LOGGER.info("EXCEPTION INFO: '"+e+"'");
					attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");			
				}
				LOGGER.info("RESULT : the page is redirected to /product/list/");
				return "redirect:/product/list";
			}else {
				attribute.addFlashAttribute("info", "Info: There are no product lines loaded in the system, you must load at least one!");
				LOGGER.info("RESULT : the page is redirected to /product/new/");
				return "redirect:/product/new/";
			}	
		}
	}

	@GetMapping("/product/list")
	public String getListProductPage(Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/list invoke the get method");
		LOGGER.info("METHOD : getListProductPage()");
		model.addAttribute("products", productsService.getAllProducts());
		LOGGER.info("RESULT : Page is displayed listproductos.html");
		return "listarproductos";	
	}
	
	@GetMapping("/product/edit/{code}")
	public String getEditProductPage(@PathVariable ("code") int code,Model model, RedirectAttributes attribute){
		LOGGER.info("CONTROLLER : ProductsController with /product/edit/{code} invoke the get method");
		LOGGER.info("METHOD : getEditProductPage(@PathVariable()");
		if(!productsService.getCheckProductById(String.valueOf(code))) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /product/list/");
			return "redirect:/product/list/";
		}
		this.products = productsService.getProductsById(String.valueOf(code));
		model.addAttribute("products", this.products);
		model.addAttribute("productlines", productlinesService.getAllProductlines());
		LOGGER.info("RESULT : Page is displayed nuevoproducto.html");
		return"nuevoproducto";
	}

	@GetMapping("/product/delete/{code}")
	public String getDeleteProductPage(@PathVariable ("code") String code , Model model, RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : ProductsController with /product/delete/{code} invoke the get method");
		LOGGER.info("METHOD : getDeleteProductPage()");
		if(!productsService.getCheckProductById(String.valueOf(code))) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /product/list/");
			return "redirect:/product/list/";
		}
		Products product = productsService.getProductsById(code);
		model.addAttribute("products", product);
		LOGGER.info("RESULT : Page is displayed eliminarproducto.html");
		return "eliminarproducto";
	}

	@GetMapping("/product/delete/confirm/{code}")
	public String getConfirmDeleteProductPage(@PathVariable("code") String code, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : ProductsController with /product/delete/confirm/{code} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteProductPage()");
		try {
			productsService.deleteProductsById(code);
			attribute.addFlashAttribute("warning", "Record deleted successfully!");
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
		}
		LOGGER.info("RESULT : the page is redirected to /product/list/");
		return "redirect:/product/list/";
	}

	@GetMapping("/product/search")
	public String getSearchProductPage(@RequestParam(name="var") String var,  Model model) {
		LOGGER.info("CONTROLLER : ProductsController with /product/search invoke the get method");
		LOGGER.info("METHOD : getSearchProductPage()");
		model.addAttribute("products", products);
		model.addAttribute("products", productsService.getProducts(var));
		LOGGER.info("RESULT : Page is displayed listarproductos.html");
		return "listarproductos";
	}

}
