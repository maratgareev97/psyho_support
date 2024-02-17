package ru.psyhohelp.test;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, " + name + "!");
    }
}