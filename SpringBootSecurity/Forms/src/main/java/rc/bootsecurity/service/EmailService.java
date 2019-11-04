package rc.bootsecurity.service;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import rc.bootsecurity.dto.MailDto;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SpringTemplateEngine templateEngine;
	
	public void sendEmail(MailDto mailDto) {
		try {

			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, 
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			
			Context context = new Context();
            context.setVariables(mailDto.getModel());
            String html = templateEngine.process("email/email-template", context);

            helper.setTo(mailDto.getTo());
            helper.setText(html, true);
            helper.setSubject(mailDto.getSubject());
            helper.setFrom(mailDto.getFrom());
            
            emailSender.send(mimeMessage);
		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}
	}
}
