package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pet_project.pet.model.User;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    private final UserService userService;


    public void sendEmail(String email, String subject, String body, Long id) {
        User currentUser = userService.getUser(id);
        if(currentUser != null) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(currentUser.getEmail());
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);

            javaMailSender.send(message);
        }
        System.err.println("User error");
    }
}
