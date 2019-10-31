package rc.bootsecurity.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rc.bootsecurity.dto.MailDto;
import rc.bootsecurity.dto.PasswordForgotDto;
import rc.bootsecurity.model.PasswordResetToken;
import rc.bootsecurity.model.User;
import rc.bootsecurity.repository.PasswordResetTokenRepository;
import rc.bootsecurity.service.EmailService;
import rc.bootsecurity.service.UserService;

@Controller
@RequestMapping("/forgot-password")
public class PasswordForgotController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordResetTokenRepository tokenRepository;
	@Autowired
	private EmailService emailService;
	
	@ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }
	
	@GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }
	
	@PostMapping
    public String processForgotPasswordForm(@ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
                                            BindingResult result,
                                            HttpServletRequest request) {
		
		// 1) Buscar el usuario del mail introducido, si no lo encontramos devolvemos el error al usuario
		User user = userService.findByMail(form.getEmail());
        if (user == null){
            result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";
        }
        
        // 2) Crear un PasswordResetToken aleatorio y guardarlo asociado al usuario
        // Por defecto hemos definido una expiracion de 24 horas
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        tokenRepository.save(token);
        
        // Configuracion del mail
        MailDto mailDto = new MailDto();
        mailDto.setFrom("no-reply@memorynotfound.com");
        mailDto.setTo(user.getMail());
        mailDto.setSubject("Password reset request");
        
        // Establecemos las variables necesarias(token, user, signature,requestUrl)
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "https://memorynotfound.com");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mailDto.setModel(model);
        emailService.sendEmail(mailDto);
        
        
        
        return "redirect:/forgot-password?success";
	}
	

}
