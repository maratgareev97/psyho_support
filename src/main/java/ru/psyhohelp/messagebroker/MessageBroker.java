package ru.psyhohelp.messagebroker;

import org.springframework.stereotype.Service;

@Service
public interface MessageBroker {
    void sendMessage(String topic, String message);
}
