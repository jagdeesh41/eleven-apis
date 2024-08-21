package kom.learning.journal;

import kom.learning.journal.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeatherServiceTest {
    @Autowired
    WeatherService weatherService;

    @Test
    void test()
    {
        Assertions.assertNotNull(weatherService.fetchRequiredWeatherFromExternalApi("Hyderabad"));
    }

}
