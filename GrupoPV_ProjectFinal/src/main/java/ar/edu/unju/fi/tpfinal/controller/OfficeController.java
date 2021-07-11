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

import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Controller
public class OfficeController {
	
	private static final Log LOGGER = LogFactory.getLog(OfficeController.class);
	
	@Autowired 
	private Office office;
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService;
	
	@GetMapping("/office/new")
	public String getNewOfficePage(Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/new invoke the get method");
		LOGGER.info("METHOD : getNewOfficePage()");
		model.addAttribute("office", officeService.getOffice());
		LOGGER.info("RESULT : Page is displayed nuevaofficina.html");
		return "nuevaoficina";
	}
	
	@PostMapping("/office/save")
	public String  saveOfficePage(@Valid @ModelAttribute("office") Office office, BindingResult result, 
			Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OfficeController with /office/save invoke the post method");
		LOGGER.info("METHOD : saveOfficePage() -- PARAMS: office'"+office+"'");
		if(result.hasErrors()) {
			model.addAttribute("office", office);
			LOGGER.info("RESULT : Page is displayed nuevaoficina.html");
			return "nuevaoficina";
		}else {
			try {
				officeService.saveOffice(office);
				attribute.addFlashAttribute("success", "The changes were saved successfully!");
			} catch (Exception e) {
				LOGGER.info("EXCEPTION INFO: '"+e+"'");
				attribute.addFlashAttribute("error", "Error: There was an error performing the requested operation!");				
			}
			LOGGER.info("RESULT : the page is redirected to /office/list/");
			return "redirect:/office/list/";
		}
	}
	
	@GetMapping("/office/list")
	public String getListOfficePage(Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/list invoke the get method");
		LOGGER.info("METHOD : getListOfficePage()");
		model.addAttribute("offices", officeService.getAllOffices());
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");
		return "listaroficinas";
	}
 
	@GetMapping("/office/edit/{code}")
	public String getEditOfficePage(@PathVariable ("code") String code, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OfficeController with /office/edit/{code} invoke the get method");
		LOGGER.info("METHOD : getEditOfficePage()");
		if(!officeService.getCheckOfficeById(code)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /office/list/");
			return "redirect:/office/list/";
		}
		this.office = officeService.getOfficeById(code);
		model.addAttribute("office", this.office);
		LOGGER.info("RESULT : Page is displayed nuevaoficina.html");
		return "nuevaoficina";
	}
	
	@GetMapping("/office/delete/{code}")
	public String getDeleteOfficePage(@PathVariable ("code") String code, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OfficeController with /office/delete/{code} invoke the get method");
		LOGGER.info("METHOD : getDeleteOfficePage()");
		if(!officeService.getCheckOfficeById(code)) {
			attribute.addFlashAttribute("error", "Error: The requested record does not exist.");
			LOGGER.info("RESULT : the page is redirected to /office/list/");
			return "redirect:/office/list/";
		}
		Office office = officeService.getOfficeById(code);
		model.addAttribute("office", office );
		LOGGER.info("RESULT : Page is displayed eliminaroficina.html");
		return "eliminaroficina";
	}
	
	@GetMapping("/office/delete/confirm/{code}")
	public String getConfirmDeleteOfficePage(@PathVariable("code") String code, Model model,RedirectAttributes attribute) {
		LOGGER.info("CONTROLLER : OfficeController with /office/delete/confirm/{code} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteOfficePage()");
		try {
			officeService.deleteOfficeById(code);
			attribute.addFlashAttribute("warning", "Record deleted successfully!");	
		} catch (Exception e) {
			LOGGER.info("EXCEPTION INFO: '"+e+"'");
			attribute.addFlashAttribute("error", "Error: It is not possible to delete this record.");
		}
		LOGGER.info("RESULT : the page is redirected to /office/list/");
		return "redirect:/office/list/";
	}

	@GetMapping("/office/search")
	public String getSearchOfficePage(@RequestParam(name="var") String var, Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/search invoke the get method");
		LOGGER.info("METHOD : getSearchOfficePage()");
		//model.addAttribute("code", var);
		model.addAttribute("offices", officeService.getOffices(var));
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");
		return "listaroficinas";
	}

}
