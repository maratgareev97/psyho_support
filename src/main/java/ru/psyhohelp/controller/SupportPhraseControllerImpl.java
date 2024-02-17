package ru.psyhohelp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.psyhohelp.configuration.*;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.service.SupportPhraseServiceImpl;
import ru.psyhohelp.service.SupportPhraseService;
import ru.psyhohelp.dao.SupportPhraseDao;

import java.io.IOException;

import java.util.List;

@Controller
public class SupportPhraseControllerImpl implements SupportPhraseController{

    private final SupportPhraseService supportPhraseService;
    private final ObjectMapper objectMapper = new ObjectMapper();



    public SupportPhraseControllerImpl() {
        this.supportPhraseService = new SupportPhraseServiceImpl(new SupportPhraseDao());
    }




    @GetMapping("/help-service/v1/support")
    public void getSupportPhrase(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        List<SupportPhrase> supportPhrases = supportPhraseService.getAllSupportPhrases();
        if (supportPhrases.isEmpty()) {
            objectMapper.writeValue(response.getWriter(), "Нет слов"); // Сериализация списка фраз в JSON и отправка клиенту
        } else {
            int randomIndex = (int) (Math.random() * supportPhrases.size());
            objectMapper.writeValue(response.getWriter(), supportPhrases.get(randomIndex).getPhrase()); // Сериализация списка фраз в JSON и отправка клиенту
        }
    }

    @PostMapping("/help-service/v1/support")
    public void addSupportPhrase(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SupportPhrase newPhrase = objectMapper.readValue(request.getReader(), SupportPhrase.class);
        SupportPhrase addedPhrase = supportPhraseService.addSupportPhrase(newPhrase.getPhrase());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(response.getWriter(), addedPhrase); // Сериализация добавленной фразы в JSON и отправка клиенту
    }
}
