package ArogyaSetuPlus.app.email;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender sender;

    public void sendMail(String email, String subject, String body, File file) throws Exception {
	try {
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true);

	    helper.setTo(email);
	    helper.setSubject(subject);
	    helper.setText(body, true); // Enable HTML formatting
	    helper.setFrom("your-email@gmail.com");

	    if (file != null) {
		helper.addAttachment(file.getName(), file);
	    }

	    sender.send(message);

	} catch (Exception e) {
	    throw e;
	}
    }

}
