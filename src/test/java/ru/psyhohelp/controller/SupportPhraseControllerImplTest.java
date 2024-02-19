package ru.psyhohelp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.psyhohelp.messagebroker.MessageBroker;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;
import ru.psyhohelp.service.SupportPhraseService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SupportPhraseControllerImplTest {

    @Mock
    private SupportPhraseService supportPhraseService;

    @Mock
    private SupportPhraseRepository supportPhraseRepository;

    @Mock
    private MessageBroker messageBroker;

    @InjectMocks
    private SupportPhraseControllerImpl supportPhraseController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    void testGetSupportPhrase() throws Exception {
        // Arrange
        List<SupportPhrase> supportPhrases = new ArrayList<>();
        supportPhrases.add(new SupportPhrase(1, "Test Phrase 1"));
        when(supportPhraseService.getAllSupportPhrases()).thenReturn(supportPhrases);

        // Act
        String response = supportPhraseController.getSupportPhrase();

        // Assert
        assertEquals("\"Test Phrase 1\"", response);
    }

    @Test
    void testAddSupportPhrase() {
        // Arrange
        SupportPhrase newPhrase = new SupportPhrase(1, "New Test Phrase");
        String requestBody = "{\"phrase\": \"New Test Phrase\"}";

        // Act
        supportPhraseController.addSupportPhrase("New Test Phrase");

        // Assert
        verify(supportPhraseRepository, times(1)).addSupportPhrase(newPhrase);
        verify(messageBroker, times(1)).sendMessage("newPhraseTopic", newPhrase.getPhrase());
    }
}
