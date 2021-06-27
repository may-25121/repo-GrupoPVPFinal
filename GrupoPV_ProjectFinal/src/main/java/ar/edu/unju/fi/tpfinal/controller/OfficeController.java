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
		LOGGER.info("RESULT : Page is displayed nuevaofficina.html");
		model.addAttribute("office", officeService.getOffice());
		return "nuevaoficina";
	}

	@PostMapping("/office/save")
	public String  saveOfficePage(@Valid @ModelAttribute("office") Office office, BindingResult result, Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/save invoke the post method");
		LOGGER.info("METHOD : saveOfficePage() -- PARAMS: office'"+office+"'");
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");
		if(result.hasErrors()) {
			model.addAttribute("office", office);
			return "nuevaoficina";
		}else {
			try {
				officeService.saveOffice(office);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			model.addAttribute("offices", officeService.getAllOffices());
			return "listaroficinas";	
		}
	}
	
	@GetMapping("/office/list")
	public String getListOfficePage(Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/list invoke the get method");
		LOGGER.info("METHOD : getListOfficePage()");
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");
		model.addAttribute("offices", officeService.getAllOffices());
		return "listaroficinas";
	}
	
 
	@GetMapping("/office/edit/{code}")
	public String getEditOfficePage(@PathVariable ("code") String code, Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/edit/{code} invoke the get method");
		LOGGER.info("METHOD : getEditOfficePage()");
		LOGGER.info("RESULT : Page is displayed nuevaoficina.html");
		this.office = officeService.getOfficeById(code);
		model.addAttribute("office", this.office);
		return "nuevaoficina";
	}
	
	
	@GetMapping("/office/delete/{code}")
	public String getDeleteOfficePage(@PathVariable ("code") String code, Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/delete/{code} invoke the get method");
		LOGGER.info("METHOD : getDeleteOfficePage()");
		LOGGER.info("RESULT : Page is displayed eliminaroficina.html");
		Office office = officeService.getOfficeById(code);
		model.addAttribute("office", office );
		return "eliminaroficina";
	}
	
	@GetMapping("/office/delete/confirm/{code}")
	public String getConfirmDeleteOfficePage(@PathVariable("code") String code, Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/delete/confirm/{code} invoke the get method");
		LOGGER.info("METHOD : getConfirmDeleteOfficePage()");
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");
		officeService.deleteOfficeById(code);
		model.addAttribute("offices", officeService.getAllOffices());
		return "listaroficinas";
	}
	
	@GetMapping("/office/search")
	public String getSearchOfficePage(@RequestParam(name="officecode") String officecode, @RequestParam(name="officecity") String officecity, Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/search invoke the get method");
		LOGGER.info("METHOD : getSearchOfficePage()");
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");		
		model.addAttribute("code", officecode);
		model.addAttribute("offices", officeService.getOffices(officecode, officecity));
		return "listaroficinas";
	}

	
}
