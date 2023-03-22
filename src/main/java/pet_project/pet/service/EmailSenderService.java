package pet_project.pet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pet_project.pet.model.Journal;
import pet_project.pet.model.User;

@Service
@RequiredArgsConstructor
public class EmailSenderService  {

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
    public void sendEmailRegardingDeposit(Journal journal) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tim.mukashev11@gmail.com");
        message.setTo("tim.mukashev11@gmail.com");
        message.setSubject("IMPORTANT INFORMATION REGARDING YOUR DEPOSIT");
        message.setText("Your Balance decreased to: " +  journal.getDeposit());

        javaMailSender.send(message);
    }
    public void sendWarningAboutDebt(Journal journal) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("Library employee");
        message.setTo(journal.getUser().getEmail());
        message.setSubject("VERY IMPORTANT");
        message.setText("YOU HAVE NEGATIVE BALANCE, RETURN BOOK AND PAY YOUR DEBT TO AVOID ADDITIONAL PENALTIES");

        javaMailSender.send(message);
    }
}
