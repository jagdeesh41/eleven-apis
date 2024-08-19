package kom.learning.journal.dto;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class WeatherResponseDto
{
    private Request request;
    private Location location;
    private Current current;
}
