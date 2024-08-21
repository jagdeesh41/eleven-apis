package kom.learning.journal.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class RequestDto {
    private String title;
    private String content;
    private LocalDate date;
}







