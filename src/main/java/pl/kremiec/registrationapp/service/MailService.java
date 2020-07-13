package pl.kremiec.registrationapp.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.kremiec.registrationapp.model.User;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    protected MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    protected void sendConfirmationToken(User user, String subject, String text) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
