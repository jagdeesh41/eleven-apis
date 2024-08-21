package kom.learning.journal.service;

import static kom.learning.journal.constants.Constant.*;

import kom.learning.journal.AppCache;
import kom.learning.journal.constants.Constant;
import kom.learning.journal.constants.URLS;
import kom.learning.journal.dto.StockResponseDto;
import kom.learning.journal.entity.Journal;
import kom.learning.journal.entity.Stock;
import kom.learning.journal.entity.User;
import kom.learning.journal.repo.JournalRepository;
import kom.learning.journal.repo.StockRepository;
import kom.learning.journal.repo.UserRepository;
import kom.learning.journal.repo.UserRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;
    private final RestTemplate restTemplate;
    private final StockRepository stocksRepository;
    private final UserRepositoryImpl userRepositoryImpl;
    private final AppCache appCache;

    @Value("${stock.apiKey}")
    private String apiKey;

    public void saveEntry(User user)
    {
        log.info("{}",user);
        User user1=userRepository.save(user);
        log.info("{}",user1);
    }
    public List<User> getAll()
    {
        return userRepository.findAll();
    }

    public User findById(ObjectId id)
    {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteById(ObjectId id)
    {
        userRepository.deleteById(id);

    }

    public void updateUserByUserName(User user,String userName)
    {

        User userInDb = userRepository.findByUserName(user.getUserName());
        log.info("Current user data {}",userInDb);
        if(userInDb!=null)
        {

            User updatedUser=User.builder()
                    .password(user.getPassword())
                    .journals(user.getJournals())
                    .build();
            log.info("Before saving user {}",user.getId());
            userRepository.save(updatedUser);
            log.info("After saving user {}",user.getId());
        }
    }
    public List<Journal> getAllJournalsOfUser(String userName)
    {
        User byUserName = userRepository.findByUserName(userName);
        List<Journal> saved = byUserName.getJournals();
        return saved;
    }
    public void createJournalEntryForUser(String userName, Journal journal)
    {
        journal.setDate(LocalDate.now());
        User user=userRepository.findByUserName(userName);
        journalRepository.save(journal);
        List<Journal>journals=user.getJournals();
        journals.add(journal);
        user.setJournals(journals);
        userRepository.save(user);
}

    public void deleteJournalEntry(ObjectId id)
    {
        journalRepository.deleteById(id);
    }

    public List<StockResponseDto> getStocks()
    {
        log.info("Fetching stocks");
        Map<String,String> uriVariables = new HashMap<>();
        uriVariables.put("api_token",apiKey);
        uriVariables.put("fmt","json");
        ResponseEntity<List<StockResponseDto>> currentStock = restTemplate
                .exchange(appCache.APP_CACHE.get(URLS.WEATHER_URL.toString())+"api/exchanges-list/?api_token={api_token}&fmt={fmt}",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<StockResponseDto>>() {
                },uriVariables);
        List<StockResponseDto> stockResponseDto = currentStock.getBody();
        stockResponseDto.stream().forEach(item->
        {
            Stock stock = Stock.builder()
                    .name(item.getName())
                    .country(item.getCountry())
                    .currency(item.getCurrency())
                    .operatingMIC(item.getOperatingMIC())
                    .code(item.getCode())
                    .build();
            log.info("current stock is {}",stock);
            stocksRepository.save(stock);
        });
        return stockResponseDto;

    }
    public List<User> getUserWithEmailAndSentimentAnalysis()
    {
        return userRepositoryImpl.getUsersWithEmailAndSentimentAnalysis();
    }

}