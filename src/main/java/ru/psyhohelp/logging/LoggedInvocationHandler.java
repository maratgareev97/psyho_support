package ru.psyhohelp.logging;

import ru.psyhohelp.configuration.Logged;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LoggedInvocationHandler implements InvocationHandler {
    private final Object target;


    public LoggedInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Logged.class)) {
            System.out.println("Logging before method: " + method.getName() + ", args: " + Arrays.toString(args));
        }

        Object result = method.invoke(target, args);

        if (method.isAnnotationPresent(Logged.class)) {
            System.out.println("Logging after method: " + method.getName() + ", result: " + result);
        }

        return result;
    }
}