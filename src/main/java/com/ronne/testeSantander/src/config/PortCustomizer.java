package com.ronne.testeSantander.src.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class PortCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        String port = System.getenv("PORT");
        if (port != null) {
            factory.setPort(Integer.parseInt(port));
        }
    }
}
