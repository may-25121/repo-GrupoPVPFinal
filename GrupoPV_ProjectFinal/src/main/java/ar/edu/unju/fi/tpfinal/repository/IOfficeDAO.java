package ar.edu.unju.fi.tpfinal.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Office;

public interface IOfficeDAO extends CrudRepository<Office, String> {
	
	public List<Office> findByCity(String city);

}
