package kom.learning.journal.entity;


import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Document(collection = "stocks")
public class Stock {
    @Id
    private ObjectId id;
    private String name;
    private String code;
    private String country;
    private String currency;
    private String operatingMIC;
}
