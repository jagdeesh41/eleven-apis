package kom.learning.journal;

import kom.learning.journal.config.RedisConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisServiceTest {
    @Autowired
    private RedisTemplate redisTemplateWithConfig;
//    @Autowired
//    private RedisTemplate redisTemplateWithoutConfig;
    @Test
    void test()
    {
        redisTemplateWithConfig.opsForValue().set("email","pepjaggu@gmail.com");
        redisTemplateWithConfig.opsForValue().set("jamun","gulab");
        redisTemplateWithConfig.opsForValue().set("gand","fattega");
        String email = redisTemplateWithConfig.opsForValue().get("email").toString();
        System.out.println("hi there"+email+" "+redisTemplateWithConfig.opsForValue().get("jamun")+redisTemplateWithConfig.opsForValue().get("mandi"));

    }


}
