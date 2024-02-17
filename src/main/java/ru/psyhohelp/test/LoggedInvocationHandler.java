package ru.psyhohelp.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LoggedInvocationHandler implements InvocationHandler {
    private final Object target;

    public LoggedInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Logged.class)) {
            // Логирование перед выполнением метода
            System.out.println("Before method: " + method.getName());
        }

        Object result = method.invoke(target, args);

        if (method.isAnnotationPresent(Logged.class)) {
            // Логирование после выполнения метода
            System.out.println("After method: " + method.getName());
        }

        return result;
    }
}