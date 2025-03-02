package rc.bootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rc.bootsecurity.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByMail(String mail);
    
    @Modifying
    @Query("update User u set  u.password =:password where u.id =:id")
    void updatePassword(@Param("password") String password, @Param("id") Long id );
    
}
