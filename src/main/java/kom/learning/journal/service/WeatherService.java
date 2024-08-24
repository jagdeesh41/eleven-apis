package kom.learning.journal.service;

import kom.learning.journal.AppCache;
import kom.learning.journal.enums.URLS;
import kom.learning.journal.dto.RequiredWeatherResponseDto;
import kom.learning.journal.dto.WeatherResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {
    private final RestTemplate restTemplate;
    private final AppCache appCache;
    private final RedisService redisService;
    private final RedisTemplate redisTemplate;
    @Value("${weather.apiKey}")
    private String apiKey;
    public WeatherResponseDto fetchWeatherFromExternalApi(String place)
    {
        Map<String ,String> params = new HashMap<>();
        params.put("api_key",apiKey);
        params.put("location",place);
        ResponseEntity<WeatherResponseDto> response = restTemplate.exchange(appCache.APP_CACHE.get(URLS.WEATHER_URL.toString()) + "?access_key={api_key}&query={location}"
                , HttpMethod.GET,
                null,
                WeatherResponseDto.class, params);
        log.info("response {}",response.getBody());
        return response.getBody();
    }
    public RequiredWeatherResponseDto fetchRequiredWeatherFromExternalApi(String place)
    {
        //http://api.weatherstack.com/current?access_key=484eb5b0ff73762ee1ab84862b62f3fd
        // &query=Hyderabad
        RequiredWeatherResponseDto weatherResponse = redisService.get("weather_in_" + place, RequiredWeatherResponseDto.class);
        log.info("weather response {}",weatherResponse);
        if(weatherResponse == null)
        {
            //item not in cache
            //let us cache it now
            Map<String ,String> params = new HashMap<>();
            params.put("api_key",apiKey);
            params.put("location",place);
            ResponseEntity<WeatherResponseDto> response = restTemplate.exchange(appCache.APP_CACHE.get(URLS.WEATHER_URL.toString()) + "?access_key={api_key}&query={location}"
                    , HttpMethod.GET,
                    null,
                    WeatherResponseDto.class, params);
            WeatherResponseDto responseBody = response.getBody();
            RequiredWeatherResponseDto requiredWeatherResponseDto=RequiredWeatherResponseDto.builder()
                    .isDay(responseBody.getCurrent().getIsDay())
                    .name(responseBody.getLocation().getName())
                    .country(responseBody.getLocation().getCountry())
                    .language(responseBody.getRequest().getLanguage())
                    .region(responseBody.getLocation().getRegion())
                    .build();
            log.info("response {}",requiredWeatherResponseDto);
            redisService.set("weather_in_"+place,requiredWeatherResponseDto,300L);
            System.out.println("-----?"+redisTemplate.opsForValue().get("weather_in_" + place));
            log.info("Weather Response from API");
            return requiredWeatherResponseDto;
        }
        else {
            //weather Response is not null
            //which means there is a response in the cache
            //use it send it
            log.info("Weather Response from Cache");
            return weatherResponse;
        }

    }

}