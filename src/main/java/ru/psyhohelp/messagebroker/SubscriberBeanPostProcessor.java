package ru.psyhohelp.messagebroker;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import ru.psyhohelp.annoation.Subscriber;

import java.lang.reflect.Method;

@Component
public class SubscriberBeanPostProcessor implements BeanPostProcessor {

    private final MessageBroker messageBroker;

    public SubscriberBeanPostProcessor(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Method method : bean.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(Subscriber.class)) {
                Subscriber subscriber = method.getAnnotation(Subscriber.class);
                String topic = subscriber.topic();
                // Подписываемся на топик с помощью messageBroker и передаём обработчик сообщения
                messageBroker.subscribe(topic, (message) -> {
                    try {
                        method.setAccessible(true);
                        method.invoke(bean, message);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
        return bean;
    }
}