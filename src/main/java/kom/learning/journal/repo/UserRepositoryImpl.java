package kom.learning.journal.repo;

import kom.learning.journal.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImpl {
    private final MongoTemplate mongoTemplate;


    public List<User> getUserForSomething()
    {
        Query query=new Query();
        query.addCriteria(Criteria.where("userName").is("colima").and("password").is("678"));
        List<User> users = mongoTemplate.find(query, User.class);
        log.info("{}",users);
        return users;
    }
    public List<User> getUserWithEmailAndSentimentAnalysis()
    {
        Query query=new Query();
        Criteria criteria1 = Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Criteria criteria2 = Criteria.where("sentimentAnalysis").is(true);
        query.addCriteria(criteria1.andOperator(criteria2));
        List<User> users = mongoTemplate.find(query,User.class);
        log.info("{}",users);
        return users;
    }
}