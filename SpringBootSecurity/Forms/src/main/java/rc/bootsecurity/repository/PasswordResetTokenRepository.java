package rc.bootsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rc.bootsecurity.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	PasswordResetToken findByToken(String token);
}
