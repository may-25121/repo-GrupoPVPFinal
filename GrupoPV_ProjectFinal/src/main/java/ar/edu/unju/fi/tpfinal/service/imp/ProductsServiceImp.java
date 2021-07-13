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
		productsDAO.save(products);
		
	}

	@Override
	public Products getProductsById(String code) {
		Products product = productsDAO.findById(code).get();
		return product;
	}
	
	@Override
	public Products getNuevoProducto() {
		return this.products;
	}
	

	@Override
	public List<Products> getAllProducts() {
		this.listaproductos=(List<Products>) productsDAO.findAll();
		return listaproductos;
	}

	@Override
	public void deleteProductsById(String code) {
		productsDAO.deleteById(code);
	}

	@Override
	public List<Products> getProducts(String var) {
		List<Products> products = new ArrayList<>();
		if(!var.isEmpty() && !productsDAO.findById(var).isEmpty()){
			products.add( productsDAO.findById(var).get());
		}else if (!var.isEmpty() && !productsDAO.findByProductName(var).isEmpty()){
			products = productsDAO.findByProductName(var);
		}else {
			products = (List<Products>) productsDAO.findAll();
		}
		return products  ;
	}

	@Override
	public boolean getCheckProductById(String code) {
		boolean var = false;
		if(!code.isEmpty() && !productsDAO.findById(code).isEmpty()) {
			var = true;
		}
		return var;
	}
}

	
	


