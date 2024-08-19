package kom.learning.journal.controller;

import kom.learning.journal.dto.RequestDto;
import kom.learning.journal.dto.ResponseDto;
import kom.learning.journal.entity.Journal;
import kom.learning.journal.entity.User;
import kom.learning.journal.model.Description;
import kom.learning.journal.service.JournalService;
import kom.learning.journal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
@Slf4j
public class JournalController {
    private final JournalService journalService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<Journal>> fetchJournal()
    {
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(1000)
                .body(journalService.fetchAllJournals());
    }
    @GetMapping("/fetch")
    public ResponseEntity<ResponseDto> getJournalById(@RequestParam("id") ObjectId id)
    {
        return new ResponseEntity<>(journalService.getJournalBydId(id),HttpStatus.OK);
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<ResponseDto> getJournalByIdViaPathVariable(@PathVariable("id") ObjectId id)
    {
        return new ResponseEntity<>(journalService.getJournalBydId(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ResponseDto> createEntry(@RequestBody RequestDto journal)
    {
        log.info("JournalEntryController.createEntry() {}",journal);
        return new ResponseEntity<>(journalService.createJournalEntry(journal), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<Journal> updateJournalById(@RequestParam("id") ObjectId id, @RequestBody Description description)
    {
        return new ResponseEntity<>(journalService.updateJournalById(id,description),HttpStatus.ACCEPTED);
    }

    @GetMapping("{userName}")
    public ResponseEntity<List<Journal>> getAllJournalsOfUser(@PathVariable("userName") String userName)
    {
        return ResponseEntity
                .ok()
                .body(userService.getAllJournalsOfUser(userName));
    }
    @PostMapping("{userName}")
    public ResponseEntity<Void> createEntryForUser(@PathVariable("userName") String userName,
                                                @RequestBody Journal journal)
    {
        userService.createJournalEntryForUser(userName,journal);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteJournalEntry(@PathVariable("id") String id)
    {
        log.info("{}",id);
        ObjectId objectId=new ObjectId(id);
        userService.deleteJournalEntry(objectId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/title")
    public ResponseEntity<List<Journal>> getJournalByTitle(@RequestParam("title") String title)
    {
        return ResponseEntity.ok().body(journalService.getMyJournalByTitle(title));
    }


}
