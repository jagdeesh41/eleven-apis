package kom.learning.journal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key,Class<T> dtoClass)
    {
        try {
            Object object = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            if(object!=null)
            {
                log.info("Key {}",key);
                log.info("Value {}",mapper.readValue(object.toString(),dtoClass));
                return mapper.readValue(object.toString(),dtoClass);

            }
            return null;

        } catch (Exception e) {
            log.error("Exception occurred during conversion {} ",e.getMessage());
            return null;
        }
    }
    public void set(String key,Object value,Long ttl)
    {
        try
        {
            ObjectMapper objectMapper = new ObjectMapper();
            String valueInString = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key,valueInString,ttl,TimeUnit.SECONDS);
        }
        catch (Exception e)
        {
            log.error("Exception {}",e.getMessage());
        }

    }


}
