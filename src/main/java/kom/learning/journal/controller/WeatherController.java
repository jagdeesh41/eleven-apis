package kom.learning.journal.controller;

import kom.learning.journal.dto.RequiredWeatherResponseDto;
import kom.learning.journal.dto.WeatherResponseDto;
import kom.learning.journal.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public ResponseEntity<WeatherResponseDto> fetchWeatherByPlace(@RequestParam("place") String place)
    {
        return ResponseEntity
                .ok()
                .body(weatherService.fetchWeatherFromExternalApi(place));
    }
    @GetMapping("/fetch")
    public ResponseEntity<RequiredWeatherResponseDto> fetchRequiredWeatherByPlace(@RequestParam("place") String place)
    {
        return ResponseEntity
                .ok()
                .body(weatherService.fetchRequiredWeatherFromExternalApi(place));
    }

}
