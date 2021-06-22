package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {
	
	@GetMapping("/home")
	public String getNuevoEmpleadoPage() {
		return "eliminarcliente";
	}

}
