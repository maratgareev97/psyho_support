package ru.psyhohelp.test;

import org.reflections.Reflections;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Set;

public class ApplicationContext {
    private HashMap<Class<?>, Object> instances = new HashMap<>();

    public ApplicationContext() throws Exception {
        scanAndInstantiate();
    }

    private void scanAndInstantiate() throws Exception {
        Reflections reflections = new Reflections("ru.psyhohelp.test");

        Set<Class<? extends GreetingService>> serviceClasses = reflections.getSubTypesOf(GreetingService.class);
        for (Class<? extends GreetingService> serviceClass : serviceClasses) {

            GreetingService serviceInstance = createProxyIfNeeded(serviceClass);

            instances.put(GreetingService.class, serviceInstance);
        }
    }


    private <T extends GreetingService> T createProxyIfNeeded(Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{GreetingService.class},
                new LoggedInvocationHandler(instance)
        );
    }

    public <T> T getInstance(Class<T> type) {
        return type.cast(instances.get(type));
    }

}
