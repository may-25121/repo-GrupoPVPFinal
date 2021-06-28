package ar.edu.unju.fi.tpfinal.controller;

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

@Autowired
Productlines productlines;

@Autowired
@Qualifier("productlinesServiceMysql")
private IProductlinesService productlinesService;

@GetMapping("/lineaproducto/nuevo")
public String getProductoPage(Model model) {
	model.addAttribute("productline",productlinesService.getProductlines());
	return "nuevalineaproducto";
}


@PostMapping("/lineaproducto/guardar")
public String saveLineProductoPage(@ModelAttribute("productline")Productlines productline, Model model) {

try {
	productlinesService.saveProductlines(productline);
} catch (Exception e) {
	System.out.println(e.getMessage());
}
model.addAttribute("productlines", productlinesService.getAllProductlines());

return "listarlineasproductos";
}
/*
ModelAndView modelView = new ModelAndView("listarlinaesproductos");
productlinesService.saveProductlines(productline);
modelView.addObject("productsline",productlinesService.getAllProductlines());
return modelView;
}
*/
@GetMapping("/lineaproducto/listar")
public String getListarLineaProductoPage(Model model) {
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	System.out.println(" ");
	return "listarlineasproductos";

}
@GetMapping("/lineaproducto/editar/{Line}")
public String getEditarPage(@PathVariable ("Line") String Line, Model model) {
	this.productlines = productlinesService.getProductlinesById(Line);
	model.addAttribute("productline", this.productlines);
	return "nuevalineaproducto";

}
/*
@GetMapping("/lineaproducto/borrar/{productLinea}")
public String getborrarPage(@PathVariable ("productLine") String productLine, Model model) {
	Productlines productline = productlinesService.getProductlinesById(productLine);
	model.addAttribute("productline", productline);
	return "";
}
*/
@GetMapping("/lineaproducto/borrar/confirmar/{Line}")
public String getConfirmarBorrarPage(@PathVariable("Line") String Line, Model model) {
	productlinesService.deleteProductlinesById(Line);
	model.addAttribute("productlines", productlinesService.getAllProductlines());
	return "listarlineasproductos";
}
}

