package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Productlines;
import ar.edu.unju.fi.tpfinal.repository.IProductlinesDAO;
import ar.edu.unju.fi.tpfinal.service.IProductlinesService;
@Service("productlinesServiceMysql")
public class ProductlinesServiceImp implements IProductlinesService {

	@Autowired
	IProductlinesDAO productlinesDAO;
	
	@Autowired
	private Productlines productlines;
	
	@Override
	public Productlines getProductlines() {
		// TODO Auto-generated method stub
		return this.productlines;
	}

	@Override
	public void saveProductlines(Productlines productlines) {
		productlinesDAO.save(productlines);
	}

	@Override
	public Productlines getProductlinesById(String Line) {
		Productlines productline = productlinesDAO.findById(Line).get();
		return productline;
	}

	@Override
	public List<Productlines> getAllProductlines() {
		List<Productlines> productlines = (List<Productlines>) productlinesDAO.findAll();
		return productlines;
	}

	@Override
	public void deleteProductlinesById(String Line) {
		productlinesDAO.deleteById(Line);
	}

}
