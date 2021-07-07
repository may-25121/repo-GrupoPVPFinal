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
LOGGER.info("RESULT : Page is displayed nuevalineaproducto.html");
	model.addAttribute("productlines",productlinesService.getProductlines());
	return "nuevalineaproducto";
}

@PostMapping("/productline/save")
public String saveProductLinePage(@Valid @ModelAttribute("productlines")Productlines productlines,BindingResult result, Model model, @RequestParam("file") MultipartFile image) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/save invoke the post method");
	LOGGER.info("METHOD : saveLineProductoPage() -- PARAMS: productlines'"+productlines+"'");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html"); 
	if(result.hasErrors()) {
		model.addAttribute("productlines", productlines);
		return "nuevalineaproducto";
	}else {
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
		model.addAttribute("productlines", productlinesService.getAllProductlines());
		
		return "listarlineasproductos";
	}
}

@GetMapping("/productline/list")
public String getListProductLinePage(Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/list invoke the get method");
	LOGGER.info("METHOD : getListProductLinePage()");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	System.out.println(" ");
	return "listarlineasproductos";

}
@GetMapping("/productline/edit/{Line}")
public String getProductLineEditPage(@PathVariable ("Line") String Line, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/edit/{Line} invoke the get method");
	LOGGER.info("METHOD : getProductLineEditPage()");
	LOGGER.info("RESULT : Page is displayed nuevalineaproducto.html");
	this.productlines = productlinesService.getProductlinesById(Line);
	model.addAttribute("productlines", this.productlines);
	return "nuevalineaproducto";

}

@GetMapping("/productline/delete/{Line}")
public String getProductLineDeletePage(@PathVariable ("Line") String Line, Model model) {
	LOGGER.info("CONTROLLER : ProductinesController with /productline/delete/{Line} invoke the get method");
	LOGGER.info("METHOD : getProductLineDeletePage()");
	LOGGER.info("RESULT : Page is displayed eliminarlineaproducto.html");
	Productlines productline = productlinesService.getProductlinesById(Line);
	model.addAttribute("productlines", productline);
	return "eliminarlineaproducto";
}

@GetMapping("/productline/delete/confirm/{Line}")
public String getProductLineDeleteConfirmPage(@PathVariable("Line") String Line, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /productline/delete/confirm/{Line} invoke the get method");
	LOGGER.info("METHOD : getProductLineDeleteConfirmPage()");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
	productlinesService.deleteProductlinesById(Line);
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	return "listarlineasproductos";
}

@GetMapping("/productline/search")
public String getProductLineSearchPage(@RequestParam(name="productlines") String productlines, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/buscar invoke the get method");
	LOGGER.info("METHOD : getProductLineSearchPage()");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");		
	model.addAttribute("productlines", productlines);
	model.addAttribute("productlines", productlinesService.getProductlines(productlines));
	return "listarlineasproductos";
}
}

