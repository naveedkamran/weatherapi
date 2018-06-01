package com.browngrid.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
    "com.browngrid.app.service",
    "com.browngrid.app.repository",
    "com.browngrid.app.web"//, "com.datenc.com.datenc.apputil"
})
public class SpringConfig {

}
