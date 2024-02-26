package ru.psyhohelp.service;

import ru.psyhohelp.model.SupportPhrase;

import java.util.HashSet;


public interface SupportPhraseService {

    HashSet<SupportPhrase> getAllSupportPhrases();

    public void sendSupportPhrase(SupportPhrase supportPhrase);
}
