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

/**
 * Clase OfficeController
 * Clase que responde a los eventos e invoca peticiones de Office
 * y ademas es el intermediario entre la vista y el modelo .
 * @author Yapura-Bejarano
 *
 */
@Controller
public class OfficeController {
	
	private static final Log LOGGER = LogFactory.getLog(OfficeController.class);
	
	@Autowired 
	private Office office;
	
	@Autowired
	@Qualifier("officeServiceMysql")
	private IOfficeService officeService;
	
	/**
	 * Metodo que nos permite mostrar el formulario para ingresar una nuevaoficina
	 * donde por medio del controller mostramos el template nuevaoficina.html
	 * @param model Parametro que se usa para agregar informacion al template,
	 * @return retorna el template nuevaoficina.html
	 */
	@GetMapping("/office/new")
	public String getNewOfficePage(Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/new invoke the get method");
		LOGGER.info("METHOD : getNewOfficePage()");
		model.addAttribute("office", officeService.getOffice());
		LOGGER.info("RESULT : Page is displayed nuevaofficina.html");
		return "nuevaoficina";
	}
	
	/**
	 * Metodo que sirve para capturar los valores o informacion ingresada en el template
	 * nuevapficina.html por medio del metodo POST, para mandarla y almacenarla a la base
	 * de datos.
	 * @param office parametro Modelo que captura lo ingresado en la vista.
	 * @param result parametro que caputra si existe algun error en la vista.
	 * @return retorna la vista donde se almacenan todos los customers (listaroficinas.html)
	 * o si se presenta algun error de validacion muestra nuevamente la vista nuevaoficina.html
	 */
	
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
	/**
	 * Modelo que muestra la vista donde esta la tabla de todas las Offices, la vista se
	 * llama listaroficinas.html
	 * @return retorna el modelo donde esta la vista listaroficinas.html que muestra la lista 
	 * de todos los employees.
	 */
	
	@GetMapping("/office/list")
	public String getListOfficePage(Model model) {
		LOGGER.info("CONTROLLER : OfficeController with /office/list invoke the get method");
		LOGGER.info("METHOD : getListOfficePage()");
		model.addAttribute("offices", officeService.getAllOffices());
		LOGGER.info("RESULT : Page is displayed listaroficinas.html");
		return "listaroficinas";
	}
	/**
	 * Modelo principalmente que sirve para editar informacion del objeto (Office)
	 * mostrando la vista del formulario (nuevaoficina.html) con valores que ya tiene 
	 * en la base de datos para ser modificado a eleccion. 
	 * @param code parametro que nos permite identificar el objeto a editar por medio del code
	 * @return retorna la vista nuevaoficina.html
	 */
 
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
	
	/**
	 * Metodo que permite eliminar un objeto de la vista (listaroficinas.html)
	 * atraves del id del objeto.
	 * @param code parametro que permite identificar el objeto a eliminar
	 * @return retorna la vista listaroficinas.html
	 */
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
	/**
	 * Metodo que permite confirmar antes de eliminar un objeto de la vista (listaroficinas.html)
	 * atraves del id del objeto.
	 * @param id parametro que permite identificar el objeto a eliminar
	 * @return retorna la vista listaroficinas.html
	 */
	
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

	/**
	 * Metodo que permite buscar un objeto de la vista (listaroficinas.html)
	 * atraves del id del objeto.
	 * @param var parametro que permite identificar el objeto a buscar
	 * @return retorna la vista listaroficinas.html
	 */
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
