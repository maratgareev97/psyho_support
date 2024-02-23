package ru.psyhohelp.repository;

import ru.psyhohelp.model.SupportPhrase;
import java.util.List;


public interface SupportPhraseRepository {
    void addSupportPhrase(SupportPhrase phrase);
    List<SupportPhrase> getAllSupportPhrases();
}
