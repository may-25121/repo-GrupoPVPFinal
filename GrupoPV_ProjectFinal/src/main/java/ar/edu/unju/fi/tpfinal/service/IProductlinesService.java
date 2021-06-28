package ar.edu.unju.fi.tpfinal.service;

import java.util.List;


import ar.edu.unju.fi.tpfinal.model.Productlines;


public interface IProductlinesService {

	public Productlines getProductlines();
	public void saveProductlines(Productlines productlines);
	public Productlines getProductlinesById(String Line);
	public List<Productlines> getAllProductlines();
	public void deleteProductlinesById(String Line);
}
