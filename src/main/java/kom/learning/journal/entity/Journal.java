package kom.learning.journal.entity;

import kom.learning.journal.enums.Sentiment;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "journal_entries")
public class Journal {
    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDate date;
    private Sentiment sentiment;
}
