package ar.edu.unju.fi.tpfinal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ar.edu.unju.fi.tpfinal.model.Usser;
import ar.edu.unju.fi.tpfinal.repository.IUserDAO;
import ch.qos.logback.core.encoder.Encoder;

@SpringBootTest
public class UserTest {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	IUserDAO userDAO;
	
	@Test
	void testSaveUser() {
		Usser user = new Usser();
		user.setUsername("anahi");
		user.setPassword(encoder.encode ("1234"));
		user.setRole("ADMIN");
		
		Usser oneUser = userDAO.save(user);
		
		/*User userTwo = new User();
		userTwo.setUsername("marisel");
		userTwo.setPassword(encoder.encode ("1234"));
		userTwo.setRole("EDITOR");
		
		User twoUser = userDAO.save(user);*/	
		assertEquals(oneUser.getUsername(),user.getUsername());

	}

	private boolean assertEquals(String username, String username2) {
		boolean var = false;
		if(username.equals(username2)){
			var = true;
		}
		return var;
	}

}
