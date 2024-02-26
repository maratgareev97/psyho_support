package ru.psyhohelp.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;


@Component
@ConditionalOnProperty(value = "kafka.sync.enabled", havingValue = "true", matchIfMissing = true)
public class SupportPhraseConsumer {

    @Autowired
    private SupportPhraseRepository supportPhraseRepository;

    @KafkaListener(topics = "supportPhrasesTopic", groupId = "psyho_support_group")
    public void listen(SupportPhrase supportPhrase) {
        supportPhraseRepository.addSupportPhrase(supportPhrase);
    }
}
