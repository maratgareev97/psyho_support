package ru.psyhohelp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;

@Service
public class SupportPhraseServiceImpl implements SupportPhraseService {

    private final SupportPhraseRepository supportPhraseRepository;

    @Autowired
    public SupportPhraseServiceImpl(SupportPhraseRepository supportPhraseRepository) {
        this.supportPhraseRepository = supportPhraseRepository;
    }

    @Override
    public List<SupportPhrase> getAllSupportPhrases() {
        return supportPhraseRepository.getAllSupportPhrases();
    }
}