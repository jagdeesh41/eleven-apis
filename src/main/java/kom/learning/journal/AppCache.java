package kom.learning.journal;

import kom.learning.journal.entity.Configuration;
import kom.learning.journal.repo.ConfigRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppCache {

    private final ConfigRepository configRepository;
    public Map<String, String> APP_CACHE = new HashMap<>();
    @PostConstruct
    public void init() {
        List<Configuration> allConfiguration = configRepository.findAll();
        log.info("{}",allConfiguration);
        allConfiguration.stream().forEach((item) ->
                APP_CACHE.put(item.getKey(), item.getValue()));
        log.info("{}",allConfiguration);
    }


}
