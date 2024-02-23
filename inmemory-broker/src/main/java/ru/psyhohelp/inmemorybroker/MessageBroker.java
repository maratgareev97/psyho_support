package ru.psyhohelp.inmemorybroker;

import org.springframework.stereotype.Service;
import ru.psyhohelp.inmemorybroker.MessageListener;

@Service
public interface MessageBroker {
    void sendMessage(String topic, String message);
    public void subscribe(String topic, MessageListener listener);
}