package kom.learning.journal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoiceSettings {
    private String stability;
    @JsonProperty("similarity_boost")
    private String similarityBoost;
}
