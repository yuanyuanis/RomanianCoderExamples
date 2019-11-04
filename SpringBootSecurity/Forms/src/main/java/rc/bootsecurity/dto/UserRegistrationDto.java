package rc.bootsecurity.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import rc.bootsecurity.util.constraint.ValidPassword;

public class UserRegistrationDto {
	
	@NotEmpty(message="No puede estar vacio")
	private String username;

	@NotEmpty(message="No puede estar vacio")
	private String firstName;

	@NotEmpty(message="No puede estar vacio")
	private String lastName;

	@NotEmpty(message="No puede estar vacio")
	@ValidPassword
	private String password;

	@NotEmpty(message="No puede estar vacio")
	@ValidPassword
	private String confirmPassword;

	@Email(message="Proporcione un email valido, con @y todas esas cosas")
	@NotEmpty(message="No puede estar vacio")
	private String email;

	@Email(message="Proporcione un email valido, con @y todas esas cosas")
	@NotEmpty(message="No puede estar vacio")
	private String confirmEmail;

	@AssertTrue(message="Debe aceptar los terminos y condiciones")
	private Boolean terms;
	

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmEmail() {
		return confirmEmail;
	}

	public void setConfirmEmail(String confirmEmail) {
		this.confirmEmail = confirmEmail;
	}

	public Boolean getTerms() {
		return terms;
	}

	public void setTerms(Boolean terms) {
		this.terms = terms;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	



}
