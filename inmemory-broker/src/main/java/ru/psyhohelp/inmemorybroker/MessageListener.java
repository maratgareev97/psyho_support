package ru.psyhohelp.inmemorybroker;

import org.springframework.stereotype.Component;

@Component
public interface MessageListener {
    void onMessage(String message);
}