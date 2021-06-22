package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Controller
public class OfficeController {
	
	@Autowired
	private Office office;
	
	private IOfficeService officeService;
	
	@GetMapping("/oficina/nueva")
	public String getNuevaOficinaPage(Model model) {
		model.addAttribute("oficina", office);
		return "nuevaoficina";
	}
	//me quede por aqui...
	@GetMapping("/oficina/guardar")
	public String saveOficinaPage(@ModelAttribute("oficina") Office oficina, Model model) {
		officeService.saveOffice(oficina);
		return "listaroficinas";
	}
	
	

}
