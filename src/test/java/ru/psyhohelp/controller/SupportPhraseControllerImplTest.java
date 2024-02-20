package ru.psyhohelp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ru.psyhohelp.messagebroker.MessageBroker;
import ru.psyhohelp.model.SupportPhrase;
import ru.psyhohelp.repository.SupportPhraseRepository;
import ru.psyhohelp.service.SupportPhraseService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;




@WebMvcTest(SupportPhraseControllerImpl.class)
public class SupportPhraseControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupportPhraseService supportPhraseService;

    @MockBean
    private SupportPhraseRepository supportPhraseRepository;

    @MockBean
    private MessageBroker messageBroker;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testGetSupportPhrase() throws Exception {
        SupportPhrase phrase = new SupportPhrase(1, "Keep going!");
        when(supportPhraseService.getAllSupportPhrases()).thenReturn(Arrays.asList(phrase));

        mockMvc.perform(get("/help-service/v1/support"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(phrase.getPhrase())));

        verify(supportPhraseService, times(1)).getAllSupportPhrases();
    }

    @Test
    public void testAddSupportPhrase() throws Exception {
        String newPhraseText = "You can do it!";
        doNothing().when(supportPhraseRepository).addSupportPhrase(any(SupportPhrase.class));
        doNothing().when(messageBroker).sendMessage(eq("newPhraseTopic"), eq(newPhraseText));

        mockMvc.perform(post("/help-service/v1/support")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPhraseText))
                .andExpect(status().isOk());

        verify(supportPhraseRepository, times(1)).addSupportPhrase(any(SupportPhrase.class));
        verify(messageBroker, times(1)).sendMessage(eq("newPhraseTopic"), eq(newPhraseText));
    }
}
