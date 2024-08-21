package kom.learning.journal.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean(name = "redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory()
    {
        return new LettuceConnectionFactory();
    }
    @Bean(name = "redisTemplateWithConfig")
    public  RedisTemplate<Object,Object> rTWC(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<Object,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
//    @Bean(name = "redisTemplateWithoutConfig")
//    public RedisTemplate<String,String> rTWNC()
//    {
//        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
//        return redisTemplate;
//    }

}
