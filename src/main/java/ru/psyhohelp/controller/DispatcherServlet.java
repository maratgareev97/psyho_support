package ru.psyhohelp.controller;

import ru.psyhohelp.ApplicationContext;
import ru.psyhohelp.configuration.Controller;
import ru.psyhohelp.configuration.GetMapping;
import ru.psyhohelp.configuration.PostMapping;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@WebServlet(urlPatterns = "/*")
public class DispatcherServlet extends HttpServlet {
    private ApplicationContext applicationContext;


    public DispatcherServlet() {
    }

    @Override
    public void init() {
        try {
            applicationContext = new ApplicationContext();
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException("Failed to initialize ApplicationContext", e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getPathInfo();

        if (path == null) {
            path = "";
        }

        Method method = findMethod(path, request.getMethod());

        if (method != null) {
            Object controller = applicationContext.getInstance(method.getDeclaringClass());
            System.out.println("Этот прокси создан для " + method.getDeclaringClass().getSimpleName() + "? " + Proxy.isProxyClass(controller.getClass()));
            try {
                method.invoke(controller, request, response);
            } catch (IllegalAccessException | InvocationTargetException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    private Method findMethod(String path, String httpMethod) {
        for (Class<?> beanClass : applicationContext.getRegisteredClasses()) {
            if (beanClass.isAnnotationPresent(Controller.class)) {
                for (Method method : beanClass.getDeclaredMethods()) {
                    if ((httpMethod.equals("GET") && method.isAnnotationPresent(GetMapping.class) && method.getAnnotation(GetMapping.class).value().equals(path)) ||
                        (httpMethod.equals("POST") && method.isAnnotationPresent(PostMapping.class) && method.getAnnotation(PostMapping.class).value().equals(path))) {
                        return method;
                    }
                }
            }
        }
        return null;
    }
}
