package ru.psyhohelp.test;


public interface GreetingService {
    @Logged
    void sayHello(String name);
}