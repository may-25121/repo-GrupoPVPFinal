package ar.edu.unju.fi.tpfinal.service;

import java.util.List;


import ar.edu.unju.fi.tpfinal.model.Products;


public interface IProductsService {
	
    public Products getProducts();
    public void saveProducs(Products products);
	public Products getProductsById(int code);
	public Products getNuevoProducto();
	public List<Products> getAllProducts();
	public void deleteProductsById(int code);
	public List<Products> getProducts(String productCode);
	

}
