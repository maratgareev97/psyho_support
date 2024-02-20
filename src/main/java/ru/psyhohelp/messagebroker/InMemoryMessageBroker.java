package ru.psyhohelp.messagebroker;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class InMemoryMessageBroker implements MessageBroker {
    private Map<String, Set<MessageListener>> subscribers = new HashMap<>();

    @Override
    public void sendMessage(String topic, String message) {
        Set<MessageListener> listeners = subscribers.get(topic);
        if (listeners != null) {
            for (MessageListener listener : listeners) {
                listener.onMessage(message);
            }
        }
    }

    @Override
    public void subscribe(String topic, MessageListener listener) {
        subscribers.computeIfAbsent(topic, k -> new HashSet<>()).add(listener);
    }
}