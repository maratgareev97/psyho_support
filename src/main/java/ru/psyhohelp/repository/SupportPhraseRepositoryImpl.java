package ru.psyhohelp.repository;

import org.springframework.stereotype.Repository;
import ru.psyhohelp.model.SupportPhrase;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SupportPhraseRepositoryImpl implements SupportPhraseRepository {

    private List<SupportPhrase> phrases = new ArrayList<>();

    @Override
    public void addSupportPhrase(SupportPhrase phrase) {
        phrases.add(phrase);
    }

    @Override
    public List<SupportPhrase> getAllSupportPhrases() {
        return phrases;
    }
}
