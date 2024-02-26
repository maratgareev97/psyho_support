package ru.psyhohelp.service;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;

@Service
public class SupportPhraseServiceImpl implements SupportPhraseService {
    @Autowired
    private KafkaTemplate<String, SupportPhrase> kafkaTemplate;

    private final SupportPhraseRepository supportPhraseRepository;

    @Value("${kafka.sync.enabled:true}")
    private boolean kafkaSyncEnabled;


    @Autowired
    public SupportPhraseServiceImpl(SupportPhraseRepository supportPhraseRepository) {
        this.supportPhraseRepository = supportPhraseRepository;
    }

    @Override
    public HashSet<SupportPhrase> getAllSupportPhrases() {
        return supportPhraseRepository.getAllSupportPhrases();
    }

    @Override
    public void sendSupportPhrase(SupportPhrase supportPhrase) {

        if (kafkaSyncEnabled) {
            String key = String.valueOf(supportPhrase.getId());
            kafkaTemplate.send("supportPhrasesTopic", key, supportPhrase);
        } else {
            supportPhraseRepository.addSupportPhrase(supportPhrase);
        }
    }
}