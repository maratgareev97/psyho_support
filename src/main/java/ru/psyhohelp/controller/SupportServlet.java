package ru.psyhohelp.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ru.psyhohelp.dao.SupportPhraseDao;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.service.SupportPhraseService;

@WebServlet("/help-service/v1/support")
public class SupportServlet extends HttpServlet {
    private final SupportPhraseService supportPhraseService = new SupportPhraseService(new SupportPhraseDao());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        List<SupportPhrase> supportPhrases = supportPhraseService.getAllSupportPhrases();

        if (supportPhrases.isEmpty()) {
            out.println("Нет слов");
        } else {
            int randomIndex = (int) (Math.random() * supportPhrases.size());
            out.println(supportPhrases.get(randomIndex).getPhrase());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        StringBuilder requestBody = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
        }


        String newPhrase = requestBody.toString();
        SupportPhrase addedPhrase = supportPhraseService.addSupportPhrase(newPhrase);

        PrintWriter out = response.getWriter();
        out.println("Ваша фраза добавлена. ID: " + addedPhrase.getId());
    }
}