package ru.psyhohelp.annoation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Аннотация применима к методам
@Retention(RetentionPolicy.RUNTIME) // Аннотация доступна во время выполнения
public @interface Subscriber {
    String topic() default ""; // Тема, на которую подписывается метод
}