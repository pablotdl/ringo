package ar.edu.unicen.ringo.mockapp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * A web application initializer that registers the Spring dispatcher servlet.
 * @author psaavedra
 */
public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        ServletRegistration.Dynamic registration = servletContext.addServlet(
                "dispatcher", new DispatcherServlet());
        if (registration == null) {
            return;
        }
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }

}
