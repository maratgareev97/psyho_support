package ru.psyhohelp;

import org.reflections.Reflections;
import ru.psyhohelp.configuration.Controller;
import ru.psyhohelp.service.SupportPhraseService;
import ru.psyhohelp.test.LoggedInvocationHandler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ApplicationContext {
    private final Map<Class<?>, Object> instances = new HashMap<>();
    private final List<Class<?>> registeredClasses = new ArrayList<>();

    public ApplicationContext() throws Exception {
        scanAndInstantiate();
    }

    private void scanAndInstantiate() throws Exception {
        Reflections reflections = new Reflections("ru.psyhohelp");
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(Controller.class);
        instantiateAnnotatedClasses(controllerClasses);

        Set<Class<? extends SupportPhraseService>> serviceClasses = reflections.getSubTypesOf(SupportPhraseService.class);
        for (Class<? extends SupportPhraseService> serviceClass : serviceClasses) {
            SupportPhraseService serviceInstance = createProxyIfNeeded(serviceClass);
            instances.put(SupportPhraseService.class, serviceInstance);
        }

    }


    private <T extends SupportPhraseService> T createProxyIfNeeded(Class<T> clazz) throws Exception {
        T instance = clazz.getDeclaredConstructor().newInstance();
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class<?>[]{SupportPhraseService.class},
                new LoggedInvocationHandler(instance)
        );
    }

    private void instantiateAnnotatedClasses(Set<Class<?>> classes) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        for (Class<?> clazz : classes) {
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            if (constructors.length != 1) {
                throw new RuntimeException("Класс должен иметь ровно один конструктор " + clazz.getName());
            }
            Constructor<?> constructor = constructors[0];
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            Object instance = constructor.newInstance();
            instances.put(clazz, instance);
            registeredClasses.add(clazz);
        }
    }


    public <T> T getInstance(Class<T> type) {
        return (T) instances.get(type);
    }

    public List<Class<?>> getRegisteredClasses() {
        return registeredClasses;
    }
}