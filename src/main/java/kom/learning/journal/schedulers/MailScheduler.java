package kom.learning.journal.schedulers;

import kom.learning.journal.AppCache;
import kom.learning.journal.entity.Journal;
import kom.learning.journal.entity.User;
import kom.learning.journal.repo.UserRepositoryImpl;
import kom.learning.journal.service.EmailService;
import kom.learning.journal.service.SentimentAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

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
        String sentiment = "Ai can impact everyone every field";
        emailService.sendEmail("vikasreddytadirocks@gmail.com","Sentiment for last 7 days is ",sentiment);
//        for(User user:userWithEmailAndSentimentAnalysis)
//        {
////            List<Journal> journals = user.getJournals();
////            List<String> filteredJournals = journals.stream()
////                    .filter(journal -> journal.getDate().isAfter(LocalDate.now().minus(7, ChronoUnit.DAYS)))
////                    .map(Journal::getContent)
////                    .toList();
////            String entry = String.join(" ", filteredJournals);
////            String sentiment = sentimentAnalysisService.getSentiment(entry);
//
//        }
    }
//    @Scheduled(cron = "0/5 * * * * ?")
    public void clearAppCahce()
    {
        appCache.init();
    }

}
