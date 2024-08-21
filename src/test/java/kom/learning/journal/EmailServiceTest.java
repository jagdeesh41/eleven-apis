package kom.learning.journal;

import kom.learning.journal.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;

//    @Test
//    void testSendMail()
//    {
//        emailService.sendEmail("pepjaggu@gmail.com",
//                "Testing java mail sender",
//                "Hi app kaise huu");
//    }


}
