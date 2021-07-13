package ar.edu.unju.fi.tpfinal.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unju.fi.tpfinal.model.Usser;

public interface IUserDAO extends CrudRepository<Usser, String> {
	
	public Optional<Usser> findByUsername(String username);

}
