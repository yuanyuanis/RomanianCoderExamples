package rc.bootsecurity.db;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import rc.bootsecurity.model.Role;
import rc.bootsecurity.model.User;
import rc.bootsecurity.repository.RoleRepository;
import rc.bootsecurity.repository.UserRepository;

@Service
public class DbInit implements CommandLineRunner {
    private static final String ADMIN = "ADMIN";
	private static final String MANAGER = "MANAGER";
	private static final String ACCESS = "ACCESS";
	private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepositor) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepositor;
    }

    @Override
    public void run(String... args) {
    	/*
    	// Delete all
        this.userRepository.deleteAll();
        this.roleRepository.deleteAll();
        
        // Create roles
        roleRepository.save(new Role(ADMIN));
        roleRepository.save(new Role(MANAGER));
        roleRepository.save(new Role(ACCESS));
        
        // Crete users
        User dan = new User("juan",passwordEncoder.encode("juan123"),"jibanez27@gmail.com");
        User admin = new User("admin",passwordEncoder.encode("admin123"),"juanibanezblanco@yahoo.es");
        User manager = new User("manager",passwordEncoder.encode("manager123"), "masfuertequelodio9@gmail.com");

        List<User> users = Arrays.asList(dan,admin,manager);
        
        

        // Save to db
        this.userRepository.saveAll(users);
        */
    }

}
