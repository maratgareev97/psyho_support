package ru.psyhohelp.inmemorybroker;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.psyhohelp.inmemorybroker.MessageListener;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;

@Component
public class SupportPhraseSubscriber implements MessageListener {

    private final SupportPhraseRepository repository;
    private final MessageBroker messageBroker;

    @Autowired
    public SupportPhraseSubscriber(SupportPhraseRepository repository, MessageBroker messageBroker) {
        this.repository = repository;
        this.messageBroker = messageBroker;
    }

    @PostConstruct
    public void subscribe() {
        messageBroker.subscribe("newPhraseTopic", this);
    }

    @Override
    public void onMessage(String message) {
        SupportPhrase newPhrase = new SupportPhrase();
        newPhrase.setPhrase(message);
        repository.addSupportPhrase(newPhrase);
    }
}