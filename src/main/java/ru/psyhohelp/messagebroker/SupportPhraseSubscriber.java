package ru.psyhohelp.messagebroker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;

@Component
public class SupportPhraseSubscriber implements MessageListener {

    private final SupportPhraseRepository repository;

    @Autowired
    public SupportPhraseSubscriber(SupportPhraseRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onMessage(String message) {
        SupportPhrase newPhrase = new SupportPhrase();
        newPhrase.setPhrase(message);
        repository.addSupportPhrase(newPhrase);
    }
}
