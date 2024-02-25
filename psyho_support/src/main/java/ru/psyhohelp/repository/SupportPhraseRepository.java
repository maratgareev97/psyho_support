package ru.psyhohelp.repository;

import ru.psyhohelp.model.SupportPhrase;

import java.util.HashSet;

public interface SupportPhraseRepository {
    void addSupportPhrase(SupportPhrase phrase);
    HashSet<SupportPhrase> getAllSupportPhrases();
}
