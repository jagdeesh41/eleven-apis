package kom.learning.journal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Location {
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;
    @JsonProperty("timezone_id")
    private String timezoneId;
    private String localtime;
    @JsonProperty("localtime_epoch")
    private int localtimeEpoch;
    private String utcOffset;
}
