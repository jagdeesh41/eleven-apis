package kom.learning.journal.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RequiredWeatherResponseDto {
    private String language;
    private String isDay;
    private String name;
    private String country;
    private String region;
}
