package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Office;
import ar.edu.unju.fi.tpfinal.repository.IOfficeDAO;
import ar.edu.unju.fi.tpfinal.service.IOfficeService;

@Service("officeServiceMysql")
public class OfficeServiceImp implements IOfficeService{

	@Autowired
	IOfficeDAO officeDAO;
	
	@Autowired
	private Office office;
	
	@Override
	public Office getOffice() {
		return this.office;
	}

	@Override
	public void saveOffice(Office office) {
		officeDAO.save(office);
	}

	@Override
	public Office getOfficeById(String code) {
		Office office = officeDAO.findById(code).get(); 
		return office;
	}
	
	@Override
	public List<Office> getAllOffices() {
		List<Office> offices = (List<Office>) officeDAO.findAll(); 
		return offices;
	}

	@Override
	public void deleteOfficeById(String code) {
		officeDAO.deleteById(code);
	}

}
