package ar.edu.unju.fi.tpfinal.service;

import ar.edu.unju.fi.tpfinal.model.Usser;

public interface IUsserService {
	
	public void create(Usser usser);
	
	public Usser findByUserName(String username);
	
	
}
