package ru.psyhohelp.service;

import ru.psyhohelp.configuration.Logged;
import ru.psyhohelp.model.SupportPhrase;

import java.util.List;

public interface SupportPhraseService {
    @Logged
    public SupportPhrase addSupportPhrase(String phrase) ;

    @Logged
    public List<SupportPhrase> getAllSupportPhrases();
}
