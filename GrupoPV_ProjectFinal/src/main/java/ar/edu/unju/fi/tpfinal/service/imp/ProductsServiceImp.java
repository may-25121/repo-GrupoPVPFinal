package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Products;

import ar.edu.unju.fi.tpfinal.repository.IProductsDAO;
import ar.edu.unju.fi.tpfinal.service.IProductsService;
@Service("productsServiceMysql")
public class ProductsServiceImp implements IProductsService{

	@Autowired 
	private IProductsDAO productsDAO;
	@Autowired
	private Products products;
	
	private List<Products> listaproductos=new ArrayList<Products>();
	
	@Override
	public Products getProducts() {
		
		return this.products;
	}

	@Override
	public void saveProducs(Products products) {
		// TODO Auto-generated method stub
		productsDAO.save(products);
		
	}

	@Override
	public Products getProductsById(int code) {
		// TODO Auto-generated method stub
		Products products = productsDAO.findById(code).get();
		return products;
	}
	
	@Override
	public Products getNuevoProducto() {
		// TODO Auto-generated method stub
		return this.products;
	}
	

	@Override
	public List<Products> getAllProducts() {
		// TODO Auto-generated method stub
		this.listaproductos=(List<Products>) productsDAO.findAll();
		return listaproductos;
	}

	@Override
	public void deleteProductsById(int code) {
		// TODO Auto-generated method stub
		productsDAO.deleteById(code);
	}

	
	

}
