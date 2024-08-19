package kom.learning.journal.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Request {
    private String type;
    private String query;
    private String language;
    private String unit;
}
