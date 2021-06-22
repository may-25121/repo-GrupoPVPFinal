package ar.edu.unju.fi.tpfinal.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unju.fi.tpfinal.model.Office;

public interface IOfficeService {
	
	public Office getOffice();
	public void saveOffice(Office office);
	public Optional<Office> searchOffice(Long code);
	public List<Office> getAllOffices();
	public void deleteOffice(Long code);

}
