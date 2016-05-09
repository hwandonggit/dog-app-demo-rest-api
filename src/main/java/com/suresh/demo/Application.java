package com.suresh.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mangofactory.swagger.plugin.EnableSwagger;

/*
 * Spring Boot application for configuration.
 */

@EnableAutoConfiguration 
@ComponentScan(basePackages = "com.suresh.demo")
@EnableJpaRepositories("com.suresh.demo.dao.jpa") 
@EnableSwagger
public class Application extends SpringBootServletInitializer {

	
	
    private static final Class<Application> applicationClass = Application.class;
    private static final Logger log = LoggerFactory.getLogger(applicationClass);

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    

}
