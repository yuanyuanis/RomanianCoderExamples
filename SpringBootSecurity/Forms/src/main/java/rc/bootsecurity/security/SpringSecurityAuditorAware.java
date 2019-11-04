package rc.bootsecurity.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import rc.bootsecurity.model.User;

public class SpringSecurityAuditorAware implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			Optional<User> op = Optional.empty();
			return op;
		}
		User user = ((UserPrincipal) authentication.getPrincipal()).getUser();
		Optional<User> opt = Optional.of(user);
		return opt;
	}

}
