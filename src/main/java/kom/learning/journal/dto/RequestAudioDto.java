package kom.learning.journal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestAudioDto {
    private String text;
    @JsonProperty("model_id")
    private String modelId;
    @JsonProperty("voice_settings")
    private VoiceSettings voiceSettings;
}
