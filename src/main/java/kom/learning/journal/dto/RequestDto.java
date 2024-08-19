package kom.learning.journal.dto;

import lombok.*;

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
    private Date date;
}







