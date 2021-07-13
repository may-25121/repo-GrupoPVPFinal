package ar.edu.unju.fi.tpfinal.service.imp;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Usser;
import ar.edu.unju.fi.tpfinal.repository.IUserDAO;
import ar.edu.unju.fi.tpfinal.service.IUsserService;


@Service
public class UsserServiceImp implements IUsserService{
	
	@Autowired
	IUserDAO userDAO;

	@Override
	public void create(Usser usser) {
		String pw = usser.getPassword();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
		usser.setPassword(bCryptPasswordEncoder.encode(pw));
		userDAO.save(usser);
	}
	
	@Override
	public Usser findByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}


}
