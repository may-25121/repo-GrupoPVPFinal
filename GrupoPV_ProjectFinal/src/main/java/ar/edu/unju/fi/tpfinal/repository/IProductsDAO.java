package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import ar.edu.unju.fi.tpfinal.model.Products;

public interface IProductsDAO  extends CrudRepository<Products, Integer> {

	//public List<Products> findByName(String productname);
}
