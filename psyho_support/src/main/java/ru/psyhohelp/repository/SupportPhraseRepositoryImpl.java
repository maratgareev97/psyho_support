package ru.psyhohelp.repository;

import org.springframework.stereotype.Repository;
import ru.psyhohelp.model.SupportPhrase;

import java.util.HashSet;


@Repository
public class SupportPhraseRepositoryImpl implements SupportPhraseRepository {

    private HashSet<SupportPhrase> phrases = new HashSet<>();

    @Override
    public void addSupportPhrase(SupportPhrase phrase) {
        phrases.add(phrase);
    }

    @Override
    public HashSet<SupportPhrase> getAllSupportPhrases() {
        return phrases;
    }
}
