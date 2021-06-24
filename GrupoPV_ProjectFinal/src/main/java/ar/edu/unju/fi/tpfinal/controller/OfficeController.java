package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Controller
public class OfficeController {
	
	@Autowired Office office;
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService;
	
	@GetMapping("/oficina/nueva")
	public String getNuevaOficinaPage(Model model) {
		model.addAttribute("office", officeService.getOffice());
		return "nuevaoficina";
	}

	@PostMapping("/oficina/guardar")
	public String  saveOficinaPage(@ModelAttribute("office") Office office, Model model) {
		try {
			officeService.saveOffice(office);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		model.addAttribute("offices", officeService.getAllOffices());
		
		return "listaroficinas";
	}
	
	@GetMapping("/oficina/listar")
	public String getListarOficinaPage(Model model) {
		model.addAttribute("offices", officeService.getAllOffices());
		System.out.println("por aqui");
		return "listaroficinas";
	}
	
 
	@GetMapping("/oficina/editar/{code}")
	public String getEditarPage(@PathVariable ("code") String code, Model model) {
		this.office = officeService.getOfficeById(code);
		model.addAttribute("office", this.office);
		return "nuevaoficina";
	}
	
	
	@GetMapping("/oficina/borrar/{code}")
	public String getborrarPage(@PathVariable ("code") String code, Model model) {
		Office office = officeService.getOfficeById(code);
		model.addAttribute("office", office );
		return "eliminaroficina";
	}
	
	@GetMapping("/oficina/borrar/confirmar/{code}")
	public String getConfirmarBorrarPage(@PathVariable("code") String code, Model model) {
		officeService.deleteOfficeById(code);
		model.addAttribute("offices", officeService.getAllOffices());
		return "listaroficinas";
	}

	
}
