package org.egdeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

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
