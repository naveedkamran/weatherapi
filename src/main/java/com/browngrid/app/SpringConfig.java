package com.browngrid.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.accept.ContentNegotiationManager;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
    "com.browngrid.app.apputil",
    //    "com.browngrid.app.service",
    "com.browngrid.app.repository", //    "com.browngrid.app.web"
})
public class SpringConfig {

    @Lazy
    @Autowired
    private ContentNegotiationManager cnm;
}
