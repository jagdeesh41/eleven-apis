package kom.learning.journal.repo;

import kom.learning.journal.entity.Stock;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StockRepository extends MongoRepository<Stock, ObjectId> {
}
