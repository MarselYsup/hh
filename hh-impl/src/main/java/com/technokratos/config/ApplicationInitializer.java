package com.technokratos.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class ApplicationInitializer implements WebApplicationInitializer {

    private static final String LOCATION = "";
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 25; // 25MB
    private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 30; // 30MB
    private static final int FILE_SIZE_THRESHOLD = 0;

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext springWebContext = new AnnotationConfigWebApplicationContext();

        springWebContext.register(DataBaseConfig.class);
        springWebContext.register(WebWvcConfig.class);
        servletContext.addListener(new ContextLoaderListener(springWebContext));

        ServletRegistration.Dynamic dispatcherServlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(springWebContext));
        dispatcherServlet.setMultipartConfig(new MultipartConfigElement(
                LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD));
        dispatcherServlet.setLoadOnStartup(1);
        dispatcherServlet.addMapping("/");
    }
}
