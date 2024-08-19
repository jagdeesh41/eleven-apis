package kom.learning.journal.controller;

import kom.learning.journal.dto.RequestAudioDto;
import kom.learning.journal.service.ElevenLabsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import static kom.learning.journal.constants.Constant.RELATIVE_AUDIO_PATH;

@RestController
@RequestMapping("eleven")
@RequiredArgsConstructor
public class ElevenLabsController {
    private final ElevenLabsService elevenLabsService;
    @PostMapping
    public void getVoice(@RequestBody RequestAudioDto requestAudioDto)
    {
        byte[] voice = elevenLabsService.getVoice(requestAudioDto);
        elevenLabsService.saveAudio(voice,RELATIVE_AUDIO_PATH+"output_audio2.mp3");
    }
}
