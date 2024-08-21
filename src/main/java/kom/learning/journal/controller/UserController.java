package kom.learning.journal.controller;

import kom.learning.journal.dto.StockResponseDto;
import kom.learning.journal.entity.User;
import kom.learning.journal.schedulers.MailScheduler;
import kom.learning.journal.service.EmailService;
import kom.learning.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final MailScheduler mailScheduler;
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok()
                .body(userService.getAll());
    }
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.saveEntry(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user,@RequestParam String userName)
    {
        userService.updateUserByUserName(user,userName);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/stock")
    public ResponseEntity<List<StockResponseDto>> getStockData()
    {
        return ResponseEntity.ok().body(userService.getStocks());
    }
    @GetMapping("/email")
    public ResponseEntity<List<User>> getEmailWithSentimentTrue()
    {
        return ResponseEntity.ok().body(userService.getUserWithEmailAndSentimentAnalysis());
    }
    @GetMapping("/email/send")
    public ResponseEntity<List<User>> sendEmail()
    {
        emailService.sendEmail("farooqueAltair@gmail.com","hi buddy","I am sending a mock mail to you");
        return ResponseEntity.ok().build();
    }
    @GetMapping("/sentimentAnalysis")
    public ResponseEntity<Void> sendSentiment()
    {
        mailScheduler.fetchUsersAndSendSentimentAnalysisMail();
        return ResponseEntity.ok().build();

    }

}
