package ru.psyhohelp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ru.psyhohelp.messagebroker.MessageBroker;
import ru.psyhohelp.model.SupportPhrase;
//import ru.psyhohelp.service.SupportPhraseServiceImpl;

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

////    @Mock
////    private SupportPhraseServiceImpl supportPhraseServiceImpl;
//
//    @Mock
//    private MessageBroker messageBroker;
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @InjectMocks
//    private SupportPhraseControllerImpl supportServlet;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testDoPost() throws Exception {
//
//        String requestBody = "{\"phrase\": \"Новая фраза\"}";
//        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
//        when(request.getReader()).thenReturn(reader);
//
//
////        SupportPhrase mockSupportPhrase = new SupportPhrase(1, "Новая фраза");
////        when(supportPhraseServiceImpl.addSupportPhrase("Новая фраза")).thenReturn(mockSupportPhrase);
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter printWriter = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(printWriter); // Настройка для правильного возврата PrintWriter
//
//
////        supportServlet.addSupportPhrase(request, response);
//
//
//        String expectedResponse = "{\"id\":1,\"phrase\":\"Новая фраза\"}";
//        assertEquals(expectedResponse, stringWriter.toString().trim());
//        Mockito.verify(messageBroker).sendMessage(Mockito.eq("newPhraseTopic"), Mockito.anyString());
//    }
//
//
//
//
//    @Test
//    void testDoGet() throws Exception {
//        List<SupportPhrase> supportPhrases = new ArrayList<>();
//
////        when(supportPhraseServiceImpl.getAllSupportPhrases()).thenReturn(supportPhrases);
//
//        StringWriter stringWriter = new StringWriter();
//        PrintWriter writer = new PrintWriter(stringWriter);
//        when(response.getWriter()).thenReturn(writer);
//
//        supportServlet.getSupportPhrase(request, response);
//
//        writer.flush();
//        String actualResponse = stringWriter.toString().trim();
//
//        if (supportPhrases.isEmpty()) {
//            assertEquals("\"Нет слов\"", actualResponse);
//
//        } else {
//            assertTrue(actualResponse.contains("Фраза 1"));
//        }
//    }


}
