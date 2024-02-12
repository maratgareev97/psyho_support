package ru.psyhohelp.service;

import java.util.List;

import ru.psyhohelp.configuration.Logged;
import ru.psyhohelp.dao.SupportPhraseDao;
import ru.psyhohelp.model.SupportPhrase;

public class SupportPhraseServiceImpl implements SupportPhraseService {


    private final SupportPhraseDao supportPhraseDao;



    public SupportPhraseServiceImpl(SupportPhraseDao supportPhraseDao) {
        this.supportPhraseDao = supportPhraseDao;
    }

    public SupportPhraseServiceImpl() {
        this.supportPhraseDao = new SupportPhraseDao();
    }

    @Override
    @Logged
    public SupportPhrase addSupportPhrase(String phrase) {
        return supportPhraseDao.addSupportPhrase(phrase);
    }

    @Override
    @Logged
    public List<SupportPhrase> getAllSupportPhrases() {
        return supportPhraseDao.getAllSupportPhrases();
    }

}
