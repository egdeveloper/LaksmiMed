package org.egdeveloper;

import org.egdeveloper.config.RepositoryConfig;
import org.egdeveloper.config.SecurityConfig;
import org.egdeveloper.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
@EnableCaching
@EnableAutoConfiguration
//@Import({WebConfig.class, RepositoryConfig.class, SecurityConfig.class})
@ComponentScan(basePackages = "org.egdeveloper")
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}
