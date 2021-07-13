package ar.edu.unju.fi.tpfinal.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.fi.tpfinal.model.Productlines;
import ar.edu.unju.fi.tpfinal.service.IProductlinesService;

@Controller
public class ProductlinesController {
	
	private static final Log LOGGER = LogFactory.getLog(ProductlinesController.class);

@Autowired
Productlines productlines;

@Autowired
@Qualifier("productlinesServiceMysql")
private IProductlinesService productlinesService;

@GetMapping("/productline/new")
public String getProductLinePage(Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/new invoke the get method");
	LOGGER.info("METHOD : getProductoPage()");
	model.addAttribute("productlines",productlinesService.getProductlines());
	LOGGER.info("RESULT : Page is displayed nuevalineaproducto.html");
	return "nuevalineaproducto";
}

@PostMapping("/productline/save")
public String saveProductLinePage(@Valid @ModelAttribute("productlines")Productlines productlines,BindingResult result, Model model, @RequestParam("file") MultipartFile image,
		RedirectAttributes attribute) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/save invoke the post method");
	LOGGER.info("METHOD : saveLineProductoPage() -- PARAMS: productlines'"+productlines+"'");
	if(result.hasErrors()) {
		model.addAttribute("productlines", productlines);
		LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
		return "nuevalineaproducto";
	}else {
		try {
			if(!image.isEmpty()) {
				//Path dirImage = Paths.get("src//main//resources//static/img");
				//String routeAbsolute = dirImage.toFile().getAbsolutePath();
				String routeAbsolute = "C://Productlines//resources";
				try {
					byte[] bytesImg = image.getBytes();
					Path routeComplete = Paths.get(routeAbsolute + "//" + image.getOriginalFilename());
					LOGGER.info("RUTA DE LA IMAGEN: '"+routeComplete+"'");
					Files.write(routeComplete, bytesImg);
					productlines.setImage(image.getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			productlinesService.saveProductlines(productlines);
			attribute.addFlashAttribute("success", "The changes were saved successfully!");
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");
		}
		LOGGER.info("RESULT :the page is redirected to /productline/list/");
		return "redirect:/productline/list/";
	}
}

@GetMapping("/productline/list")
public String getListProductLinePage(Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/list invoke the get method");
	LOGGER.info("METHOD : getListProductLinePage()");
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
	return "listarlineasproductos";

}
@GetMapping("/productline/edit/{Line}")
public String getProductLineEditPage(@PathVariable ("Line") String Line, Model model,RedirectAttributes attribute) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/edit/{Line} invoke the get method");
	LOGGER.info("METHOD : getProductLineEditPage()");
	if(!productlinesService.getCheckProductLineById(Line)) {
		attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
		LOGGER.info("RESULT : the page is redirected to /productline/list/");
		return "redirect:/productline/list/";
	}
	this.productlines = productlinesService.getProductlinesById(Line);
	model.addAttribute("productlines", this.productlines);
	LOGGER.info("RESULT : Page is displayed nuevalineaproducto.html");
	return "nuevalineaproducto";

}

@GetMapping("/productline/delete/{Line}")
public String getProductLineDeletePage(@PathVariable ("Line") String Line, Model model, RedirectAttributes attribute) {
	LOGGER.info("CONTROLLER : ProductinesController with /productline/delete/{Line} invoke the get method");
	LOGGER.info("METHOD : getProductLineDeletePage()");
	if(!productlinesService.getCheckProductLineById(Line)) {
		attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
		LOGGER.info("RESULT : the page is redirected to /productline/list/");
		return "redirect:/productline/list/";
	}
	Productlines productline = productlinesService.getProductlinesById(Line);
	model.addAttribute("productlines", productline);
	LOGGER.info("RESULT : Page is displayed eliminarlineaproducto.html");
	return "eliminarlineaproducto";
}

@GetMapping("/productline/delete/confirm/{Line}")
public String getProductLineDeleteConfirmPage(@PathVariable("Line") String Line, Model model,RedirectAttributes attribute) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/delete/confirm/{Line} invoke the get method");
	LOGGER.info("METHOD : getProductLineDeleteConfirmPage()");
	try {
		productlinesService.deleteProductlinesById(Line);
		attribute.addFlashAttribute("warning", "Record deleted successfully!");
	} catch (Exception e) {
		LOGGER.info("EXCEPTION INFO: '"+e+"'");
		attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
	}
	LOGGER.info("RESULT : Pthe page is redirected to /productline/list/");
	return "redirect:/productline/list/";
}

@GetMapping("/productline/search")
public String getProductLineSearchPage(@RequestParam(name="productlines") String productlines, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/buscar invoke the get method");
	LOGGER.info("METHOD : getProductLineSearchPage()");		
	model.addAttribute("productlines", productlines);
	model.addAttribute("productlines", productlinesService.getProductlines(productlines));
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
	return "listarlineasproductos";
}
}

