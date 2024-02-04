package ru.psyhohelp.dao;

import ru.psyhohelp.model.SupportPhrase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportPhraseDao {
    private static final Map<Integer, SupportPhrase> supportPhrases = new HashMap<>();
    private static int idCounter = 1;

    public SupportPhrase addSupportPhrase(String phrase) {
        SupportPhrase newSupportPhrase = new SupportPhrase(idCounter, phrase);
        supportPhrases.put(idCounter, newSupportPhrase);
        idCounter++;
        return newSupportPhrase;
    }

    public List<SupportPhrase> getAllSupportPhrases() {
        return List.copyOf(supportPhrases.values());
    }
}
