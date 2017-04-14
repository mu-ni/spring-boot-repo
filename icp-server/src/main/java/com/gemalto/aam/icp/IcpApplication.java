package com.gemalto.aam.icp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.WebApplicationInitializer;

@SpringBootApplication
@EnableScheduling
public class IcpApplication extends SpringBootServletInitializer implements WebApplicationInitializer {
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(IcpApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(IcpApplication.class, args);
	}
}
