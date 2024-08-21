package kom.learning.journal.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;


    public void sendEmail(String toEmail,String subject,String body)
    {
        try
        {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(toEmail);
            mail.setText(body);
            mail.setSubject(subject);

            javaMailSender.send(mail);
        }
        catch (Exception e)
        {
            log.info("Exception while sendEmail {}",e.getMessage());

        }

    }






}
