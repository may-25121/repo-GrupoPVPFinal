package ar.edu.unju.fi.tpfinal.service;

import java.util.List;

import ar.edu.unju.fi.tpfinal.model.Office;

public interface IOfficeService {
	
	public Office getOffice();
	public void saveOffice(Office office);
	public Office getOfficeById(String code);
	public List<Office> getAllOffices();
	public void deleteOfficeById(String code);
	public List<Office> getOffices(String code, String city);

}
