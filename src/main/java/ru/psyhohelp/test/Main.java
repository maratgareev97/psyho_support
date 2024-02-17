package ru.psyhohelp.test;

public class Main {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new ApplicationContext();
        GreetingService greetingService = context.getInstance(GreetingService.class);
        if (greetingService != null) {
            greetingService.sayHello("World");
        } else {
            System.out.println("GreetingService instance not found.");
        }
}}
