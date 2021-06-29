package ar.edu.unju.fi.tpfinal.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import ar.edu.unju.fi.tpfinal.model.Customer;
import ar.edu.unju.fi.tpfinal.service.ICustomerService;


@Controller
public class CustomerController {
	
	private static final Log LOGGER = LogFactory.getLog(OfficeController.class);
	
	@Autowired
	private  Customer customer;
	
	@Autowired
	@Qualifier("customerServiceMysql")
	private ICustomerService customerService;
	
	
	
	
	

}
