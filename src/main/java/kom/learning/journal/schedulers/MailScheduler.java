package kom.learning.journal.schedulers;

import kom.learning.journal.AppCache;
import kom.learning.journal.entity.Journal;
import kom.learning.journal.entity.User;
import kom.learning.journal.enums.Sentiment;
import kom.learning.journal.repo.UserRepositoryImpl;
import kom.learning.journal.service.EmailService;
import kom.learning.journal.service.SentimentAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
@EnableScheduling
public class MailScheduler {
    private final UserRepositoryImpl userRepository;
    private final SentimentAnalysisService sentimentAnalysisService;
    private final EmailService emailService;
    private final AppCache appCache;
//    @Scheduled(cron = "0/5 * * * * ?")
    public void fetchUsersAndSendSentimentAnalysisMail()
    {
        log.info("{}", new Date().toString());
        List<User> userWithEmailAndSentimentAnalysis = userRepository.getUsersWithEmailAndSentimentAnalysis();
        log.info("Sending email to these users {} ",userWithEmailAndSentimentAnalysis);
        for(User user : userWithEmailAndSentimentAnalysis)
        {
            List<Journal> journals = user.getJournals();
            List<Sentiment> sentiments = journals.stream().filter(journal -> journal.getDate().isAfter(LocalDate.now().minus(7, ChronoUnit.DAYS))).map(Journal::getSentiment).toList();
            EnumMap<Sentiment, Integer> sentimentCounts = new EnumMap<>(Sentiment.class);
            sentiments.forEach(element->
            {
                if(element != null)
                {
                    sentimentCounts.put(element,sentimentCounts.getOrDefault(element,0)+1);
                }
                int maxCount = 0;
                Sentiment mostFrequentSentiment = null;
                for(Map.Entry<Sentiment, Integer> entry : sentimentCounts.entrySet())
                {
                    if(entry.getValue()>maxCount)
                    {
                        maxCount = entry.getValue();
                        mostFrequentSentiment = entry.getKey();
                    }
                }
                if(mostFrequentSentiment!=null)
                {
                    emailService.sendEmail(user.getEmail(),"Sentiment for Last 7 days ",mostFrequentSentiment.toString());
                }
            });
        }
    }
//    @Scheduled(cron = "0/5 * * * * ?")
    public void clearAppCahce()
    {
        appCache.init();
    }

}
