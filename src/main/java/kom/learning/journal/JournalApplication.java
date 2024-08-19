package kom.learning.journal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;




@SpringBootApplication
@Slf4j
public class JournalApplication {
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args)
	{
		log.info("current Directory {}",System.getProperty("user.dir"));
		SpringApplication.run(JournalApplication.class, args);
	}

}
