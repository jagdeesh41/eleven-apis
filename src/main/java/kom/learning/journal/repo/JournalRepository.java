package kom.learning.journal.repo;

import kom.learning.journal.entity.Journal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalRepository extends MongoRepository<Journal, ObjectId> {
    List<Journal> findByTitle(String title);
    List<Journal> findAllByTitle(String title);
}
