package kom.learning.journal.repo;

import kom.learning.journal.entity.Configuration;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigRepository extends MongoRepository<Configuration, ObjectId> {
}
