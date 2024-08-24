package kom.learning.journal.service;

import kom.learning.journal.enums.URLS;
import kom.learning.journal.dto.RequestAudioDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Setter
@RequiredArgsConstructor
@Slf4j
@Service
public class ElevenLabsService {

    private final RestTemplate restTemplate;

    @Value("${eleven.apiKey}")
    private String apiKey;

    public byte[] getVoice(RequestAudioDto requestAudioDto)
    {
        log.info("{}",requestAudioDto);
        requestAudioDto.setText("Hey Farooq");
        log.info("{}",requestAudioDto);
        MultiValueMap<String ,String> headers = new HttpHeaders();
        headers.add("xi-api-key",apiKey);
        HttpEntity<RequestAudioDto> requestEntity=new HttpEntity<>(requestAudioDto,headers);
        Map<String,String> queryParams = new HashMap<>(Map.of("logStatus","true"));
        ResponseEntity<byte[]> response = restTemplate.exchange(URLS.ELEVEN_BASE_URL.toString() + "?enable_logging={logStatus}",
                HttpMethod.POST, requestEntity,byte[].class,queryParams);
        log.info("current Directory {}",System.getProperty("user.dir"));
        return response.getBody();
    }

    public void saveAudio(byte[] audioData, String filePath)
    {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(audioData);
        }
        catch (IOException e)
        {
            log.info("Exception {}",e.getMessage());
        }
    }
}
