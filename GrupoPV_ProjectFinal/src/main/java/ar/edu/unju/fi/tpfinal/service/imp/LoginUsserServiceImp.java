package ar.edu.unju.fi.tpfinal.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.tpfinal.model.Usser;
import ar.edu.unju.fi.tpfinal.repository.IUserDAO;

@Service
public class LoginUsserServiceImp implements UserDetailsService {
	
	@Autowired
	IUserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usser userFound = userDAO.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found"));
		
		List<GrantedAuthority> types = new ArrayList<>();
		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(userFound.getRole());
		types.add(grantedAuthority);
		
		UserDetails user = (UserDetails) new User(username, userFound.getPassword(),types);
 		return user;
	}
	

}
