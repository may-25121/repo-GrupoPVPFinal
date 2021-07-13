package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping({"/login","/login?error=true"})
	public String getLoginPage() {
		return "login";
	}
	

}
