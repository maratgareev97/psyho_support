package ru.psyhohelp.service;

import java.util.List;

import ru.psyhohelp.dao.SupportPhraseDao;
import ru.psyhohelp.model.SupportPhrase;

public class SupportPhraseService {
    private final SupportPhraseDao supportPhraseDao;

    public SupportPhraseService(SupportPhraseDao supportPhraseDao) {
        this.supportPhraseDao = supportPhraseDao;
    }

    public SupportPhrase addSupportPhrase(String phrase) {
        return supportPhraseDao.addSupportPhrase(phrase);
    }

    public List<SupportPhrase> getAllSupportPhrases() {
        return supportPhraseDao.getAllSupportPhrases();
    }

}
