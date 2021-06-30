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

@GetMapping("/lineaproducto/nuevo")
public String getProductoPage(Model model) {
LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/nuevo invoke the get method");
LOGGER.info("METHOD : getProductoPage()");
LOGGER.info("RESULT : Page is displayed nuevalineaproducto.html");
	model.addAttribute("productlines",productlinesService.getProductlines());
	return "nuevalineaproducto";
}


@PostMapping("/lineaproducto/guardar")
public String saveLineProductoPage(@ModelAttribute("productlines")Productlines productlines, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/guardar invoke the post method");
	LOGGER.info("METHOD : saveLineProductoPage() -- PARAMS: productlines'"+productlines+"'");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");

try {
	productlinesService.saveProductlines(productlines);
} catch (Exception e) {
	System.out.println(e.getMessage());
}
model.addAttribute("productlines", productlinesService.getAllProductlines());

return "listarlineasproductos";
}

@GetMapping("/lineaproducto/listar")
public String getListarLineaProductoPage(Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/listar invoke the get method");
	LOGGER.info("METHOD : getListarLineaProductoPage()");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	System.out.println(" ");
	return "listarlineasproductos";

}
@GetMapping("/lineaproducto/editar/{Line}")
public String getEditarPage(@PathVariable ("Line") String Line, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/editar/{Line} invoke the get method");
	LOGGER.info("METHOD : getEditarPage()");
	LOGGER.info("RESULT : Page is displayed nuevalineaproducto.html");
	this.productlines = productlinesService.getProductlinesById(Line);
	model.addAttribute("productlines", this.productlines);
	return "nuevalineaproducto";

}

@GetMapping("/lineaproducto/borrar/{Line}")
public String getBorrarPage(@PathVariable ("Line") String Line, Model model) {
	LOGGER.info("CONTROLLER : ProductinesController with /lineaproducto/borrar/{Line} invoke the get method");
	LOGGER.info("METHOD : getBorrarPage()");
	LOGGER.info("RESULT : Page is displayed eliminarlineaproducto.html");
	Productlines productline = productlinesService.getProductlinesById(Line);
	model.addAttribute("productlines", productline);
	return "eliminarlineaproducto";
}

@GetMapping("/lineaproducto/borrar/confirmar/{Line}")
public String getConfirmarBorrarPage(@PathVariable("Line") String Line, Model model) {
	LOGGER.info("CONTROLLER : ProductlinesController with /lineaproducto/borrar/confirmar/{Line} invoke the get method");
	LOGGER.info("METHOD : getConfirmarBorrarPage()");
	LOGGER.info("RESULT : Page is displayed listarlineasproductos.html");
	productlinesService.deleteProductlinesById(Line);
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	return "listarlineasproductos";
}
}

