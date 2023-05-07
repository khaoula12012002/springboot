package backEnd.springApp.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendContactMessage(ContactMessage message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("khaoulabenslimen0@gmail.com");
        mailMessage.setSubject("nouveau message de : " + message.getName());
        mailMessage.setText(message.getMessage() + "\n\nFrom: " + message.getName() + " <" + message.getEmail() + ">");
        mailSender.send(mailMessage);
    }
}
