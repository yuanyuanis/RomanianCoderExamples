package rc.bootsecurity.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.bootsecurity.dto.UserRegistrationDto;
import rc.bootsecurity.model.User;
import rc.bootsecurity.repository.UserRepository;

@Controller
@RequestMapping("/register")
public class RegisterController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@ModelAttribute("userForm")
	public UserRegistrationDto getRegisterForm() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String displayRegisterForm() {
		return "register";
	}
	

	@PostMapping
	public String registeForm(@ModelAttribute("userForm") @Valid UserRegistrationDto form, BindingResult result,
			HttpServletRequest request) {
		
		// 1) Validamos los datos de entrada con javax.validation y validaciones manuales
		if(result.hasErrors()|hayErroresEnValidacionesManuales(form, result)) {
			return "register";
		}
		
		// 2) Si se han pasado las validaciones, se guarda el nuevo usuario
		User user = new User(form.getUsername(), passwordEncoder.encode(form.getPassword()), form.getEmail(), form.getFirstName(), form.getLastName());
		userRepository.save(user);
		
		// 3) Informar al usuario de que nos hemos registrado con exito
		return "redirect:/register?success";
	}
	
	private boolean hayErroresEnValidacionesManuales(UserRegistrationDto form, BindingResult result) {
		boolean errores = false;
		// 1) Validar que las passwords son iguales
		if(!form.getPassword().equals(form.getConfirmPassword())) {
			result.rejectValue("password", null, "Las contraseñas tienen que coincidir");
			result.rejectValue("confirmPassword", null, "Las contraseñas tienen que coincidir");
			errores = true;
		}
		// 2) Validar que los mails son iguales
		if(!form.getEmail().equals(form.getConfirmEmail())) {
			result.rejectValue("email", null, "Los emails tienen que coincidir");
			result.rejectValue("email", null, "Los emails tienen que coincidir");
			errores = true;
		}
		
		// 3) Validar que no existe el mail
		User user = userRepository.findByMail(form.getEmail());
		if(user!=null) {
			result.rejectValue("email", null, "El Email ya esta registrado en la base de datos");
			result.rejectValue("confirmEmail", null, "El Email ya esta registrado en la base de datos");
			errores=  true;
		}
		// 4) Validar que no existe el userName
		user = userRepository.findByUsername(form.getUsername());
		if(user!=null) {
			result.rejectValue("username", null, "El usuario ya esta registrado en la base de datos");
			errores = true;
		}
		return errores;
	}
}
