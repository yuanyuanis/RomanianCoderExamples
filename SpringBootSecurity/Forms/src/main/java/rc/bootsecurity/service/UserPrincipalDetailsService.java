package rc.bootsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rc.bootsecurity.dto.UserRegistrationDto;
import rc.bootsecurity.model.User;
import rc.bootsecurity.repository.UserRepository;
import rc.bootsecurity.security.UserPrincipal;

@Service
public class UserPrincipalDetailsService implements UserDetailsService, UserService {
    
	@Autowired
	private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(userName);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
    
    public User findByMail(String mail) {
    	return userRepository.findByMail(mail);
    }

	@Override
	public void updatePassword(String password, Long id) {
		userRepository.updatePassword(password, id);
	}

	@Override
	public User save(UserRegistrationDto userRegistrationDto) {
	    
		return null;
	}

	@Override
	public UserDetails loadUserDetailsByMail(String mail) throws UsernameNotFoundException{
		User user = userRepository.findByMail(mail);
		if(user==null) {
			throw new UsernameNotFoundException("Invalid mail");
		}
		return new org.springframework.security.core.userdetails.User(user.getMail(), user.getPassword(), user.getRoleListAsGrantedAuthority());
	}
    
}
