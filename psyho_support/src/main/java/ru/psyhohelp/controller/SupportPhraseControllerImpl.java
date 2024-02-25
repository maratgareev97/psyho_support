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
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;

@RestController
public class SupportPhraseControllerImpl {

    private final SupportPhraseRepository supportPhraseRepository;

    private final SupportPhraseService supportPhraseService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public SupportPhraseControllerImpl(SupportPhraseRepository supportPhraseRepository, SupportPhraseService supportPhraseService) {
        this.supportPhraseRepository = supportPhraseRepository;
        this.supportPhraseService = supportPhraseService;
    }

    @GetMapping("/help-service/v1/support")
    public String getSupportPhrase() throws IOException {
        HashSet<SupportPhrase> supportPhrases = supportPhraseRepository.getAllSupportPhrases();
        if (supportPhrases.isEmpty()) {
            return "\"Нет слов\"";
        } else {
            int randomIndex = (int) (Math.random() * supportPhrases.size());
            Iterator<SupportPhrase> iterator = supportPhrases.iterator();
            SupportPhrase randomPhrase = null;
            for (int i = 0; i <= randomIndex; i++) {
                randomPhrase = iterator.next();
            }

            for (SupportPhrase s : supportPhraseService.getAllSupportPhrases()) {
                System.out.println(s.getId() + "   " + s.getPhrase());
            }
            return objectMapper.writeValueAsString(randomPhrase.getPhrase());
        }
    }

    @PostMapping("/help-service/v1/support")
    public void addSupportPhrase(@RequestBody String newPhraseText) {
        SupportPhrase newPhrase = new SupportPhrase();
        newPhrase.setId(UUID.randomUUID().toString());
        newPhrase.setPhrase(newPhraseText);

        Boolean flag = false;

        for (SupportPhrase s : supportPhraseService.getAllSupportPhrases()) {
            if (s.getPhrase().contains(newPhraseText)) {
                flag = true;
                break;
            }
        }
        if (!flag) supportPhraseService.sendSupportPhrase(newPhrase);
    }
}
