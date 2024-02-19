package ru.psyhohelp.messagebroker;

import org.springframework.stereotype.Component;

@Component
public interface MessageListener {
    void onMessage(String message);
}
