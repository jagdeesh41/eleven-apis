package kom.learning.journal.service;

import kom.learning.journal.dto.RequestDto;
import kom.learning.journal.dto.ResponseDto;
import kom.learning.journal.entity.Journal;
import kom.learning.journal.model.Description;
import kom.learning.journal.repo.JournalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JournalService {
    private final JournalRepository journalRepository;
    public ResponseDto createJournalEntry(RequestDto requestDto) {
        Journal journal = new Journal();
        journal.setDate(LocalDate.now());
        journal.setContent(requestDto.getContent());
        journal.setTitle(requestDto.getTitle());
        Journal journal1 = journalRepository.save(journal);
        return new ResponseDto(journal1.getTitle(), journal1.getContent());

    }
    public List<Journal> fetchAllJournals() {
        return journalRepository.findAll();
    }

    public ResponseDto getJournalBydId(ObjectId id) {
        Journal journal = journalRepository.findById(id).orElse(null);
        log.info("--->{}",journal.getContent());
        return ResponseDto.builder().content(journal.getContent()).title(journal.getTitle()).build();
    }

    public Journal updateJournalById(ObjectId id, Description description) {
        Journal journal = journalRepository.findById(id).orElse(null);
        log.info(" before {}", journal);
        journal.setTitle(description.getTitle());
        journal.setContent(description.getContent());
        Journal savedJournal = journalRepository.save(journal);
        log.info("after {}", journal);
        new ArrayList<>(List.of(1,2,3,4));
        return savedJournal;
    }
    public List<Journal> getMyJournalByTitle(String title)
    {
        log.info("Getting  journal Data matching with title");
        return journalRepository.findByTitle(title);

    }
}
