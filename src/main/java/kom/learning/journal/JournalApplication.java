package kom.learning.journal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;


@SpringBootApplication
@Slf4j
public class JournalApplication {
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
	@Bean
	public JavaMailSender javaMailSender()
	{
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		javaMailSender.setUsername("devipriyapepakayala@gmail.com");
		javaMailSender.setPassword("hfbonzusfbeawhmi");
		Properties props = javaMailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		javaMailSender.setJavaMailProperties(props);
		return javaMailSender;
	}

	public static void main(String[] args)
	{
		log.info("current Directory {}",System.getProperty("user.dir"));
		SpringApplication.run(JournalApplication.class, args);
	}
}
