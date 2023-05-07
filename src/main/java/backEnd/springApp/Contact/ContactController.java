package backEnd.springApp.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private EmailService emailService;
    @PostMapping
    public void sendContactMessage(@RequestBody ContactMessage message) {
        emailService.sendContactMessage(message);
    }


}
