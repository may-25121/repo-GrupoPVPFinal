package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
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

	@Override
	public List<Productlines> getProductlines(String Line) {
		List<Productlines> productlines = new ArrayList<>();
		if(!Line.isEmpty() && !productlinesDAO.findById(Line).isEmpty()) {
			productlines.add( productlinesDAO.findById(Line).get());
		
		}else {
			productlines = (List<Productlines>) productlinesDAO.findAll();
		}
		return productlines;
	
	}

	@Override
	public boolean getCheckProductLineById(String Line) {
		boolean var =false;
		if(!Line.isEmpty() && !productlinesDAO.findById(Line).isEmpty()) {
			var = true;
		}
		return var;
	}

}
