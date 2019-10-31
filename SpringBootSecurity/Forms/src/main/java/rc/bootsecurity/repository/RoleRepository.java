package rc.bootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rc.bootsecurity.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
	
	
}
