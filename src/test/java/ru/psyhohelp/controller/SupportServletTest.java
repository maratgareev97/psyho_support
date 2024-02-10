package ru.psyhohelp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.service.SupportPhraseService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class SupportServletTest {

    @Mock
    private SupportPhraseService supportPhraseService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private SupportServlet supportServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDoPost() throws Exception {

        String requestBody = "Новая фраза";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(request.getReader()).thenReturn(reader);

        SupportPhrase mockSupportPhrase = new SupportPhrase(1, "Новая фраза");
        when(supportPhraseService.addSupportPhrase(requestBody)).thenReturn(mockSupportPhrase);


        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));


        supportServlet.doPost(request, response);


        String expectedResponse = "Ваша фраза добавлена. ID: 1";
        assertEquals(expectedResponse, stringWriter.toString().trim());
    }

    @Test
    void testDoGet() throws Exception {
        //Я вообще не понял, как обрабатывать рандом в тесте

        List<SupportPhrase> supportPhrases = new ArrayList<>();
        // supportPhrases.add(new SupportPhrase(1, "Фраза 1"));


        when(supportPhraseService.getAllSupportPhrases()).thenReturn(supportPhrases);


        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);


        supportServlet.doGet(request, response);


        writer.flush();
        String actualResponse = stringWriter.toString().trim();


        if (supportPhrases.isEmpty()) {
            assertEquals("Нет слов", actualResponse);
        } else {
            assertTrue(actualResponse.contains("Фраза 1"));
        }
    }


}
