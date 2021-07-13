package ar.edu.unju.fi.tpfinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.edu.unju.fi.tpfinal.service.IUsserService;

@Controller
public class MainController {
	@Autowired
	private IUsserService userService;
	
	@RequestMapping({"/","login","logout"})
	public String getLoginPage(Model model) {
		return "login";
	}
	

}
