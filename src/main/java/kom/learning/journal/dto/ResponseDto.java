package kom.learning.journal.dto;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class ResponseDto {
    private String title;
    private String content;
}
