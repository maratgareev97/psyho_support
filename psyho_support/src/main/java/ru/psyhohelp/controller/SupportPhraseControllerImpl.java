package ru.psyhohelp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;
import ru.psyhohelp.service.SupportPhraseService;

import java.io.IOException;
import java.util.List;

@RestController
public class SupportPhraseControllerImpl {

    private final SupportPhraseRepository supportPhraseRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SupportPhraseControllerImpl(SupportPhraseRepository supportPhraseRepository) {
        this.supportPhraseRepository = supportPhraseRepository;
    }

    @GetMapping("/help-service/v1/support")
    public String getSupportPhrase() throws IOException {
        List<SupportPhrase> supportPhrases = supportPhraseRepository.getAllSupportPhrases();
        if (supportPhrases.isEmpty()) {
            return "\"Нет слов\"";
        } else {
            int randomIndex = (int) (Math.random() * supportPhrases.size());
            return objectMapper.writeValueAsString(supportPhrases.get(randomIndex).getPhrase());
        }
    }

    @PostMapping("/help-service/v1/support")
    public void addSupportPhrase(@RequestBody String newPhraseText) {
        SupportPhrase newPhrase = new SupportPhrase();
        newPhrase.setPhrase(newPhraseText);

        supportPhraseRepository.addSupportPhrase(newPhrase);
    }
}
