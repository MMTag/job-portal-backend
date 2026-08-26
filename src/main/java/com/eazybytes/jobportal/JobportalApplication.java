package com.eazybytes.jobportal;

import com.eazybytes.jobportal.config.web.WebConfig;
import com.eazybytes.jobportal.security.util.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@SpringBootApplication
@EnableConfigurationProperties(value = {CorsProperties.class})
public class JobportalApplication {

	static void main(String[] args) {
		SpringApplication.run(JobportalApplication.class, args);
	}

}
