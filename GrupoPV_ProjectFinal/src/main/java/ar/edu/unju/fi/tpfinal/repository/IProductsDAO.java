package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import ar.edu.unju.fi.tpfinal.model.Products;

public interface IProductsDAO  extends CrudRepository<Products, String> {

	public List<Products> findByProductName(String var);
}
