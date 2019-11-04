package rc.bootsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;

import rc.bootsecurity.dto.UserRegistrationDto;
import rc.bootsecurity.model.User;

public interface UserService {
	 
	 User findByMail(String mail);
	 User findByUsername(String username);
	 void updatePassword(String password, Long id);
	 User save(UserRegistrationDto userRegistrationDto);
	 UserDetails loadUserDetailsByMail(String mail);
}
