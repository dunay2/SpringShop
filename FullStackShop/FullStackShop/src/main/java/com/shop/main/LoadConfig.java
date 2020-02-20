package com.shop.main;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import lombok.extern.slf4j.Slf4j;

import com.shop.app.repository.EmployeeRepository;
import com.shop.app.repository.UserDTORepository;

import com.shop.app.model.EmployeeDTO;
import com.shop.app.model.UserDTO;

/**
 * Configuration class: preloads some data for testing
 * handles property file load for language configuration
 * @author ashh412
 *
 */

@EnableJpaRepositories(basePackages = { "com.shop.app.repository","com.shop.auth.repository" })
@EntityScan(basePackages = { "com.shop.app.model","com.shop.auth.model" }) 
@ComponentScan(basePackages = { "com.shop.rest","com.shop.auth.service" })
@Configuration
@Slf4j
class LoadConfiguration {

	@Bean
	CommandLineRunner initEmployeeDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new EmployeeDTO("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new EmployeeDTO("Frodo Baggins", "thief")));
		};
	}

	@Bean
	CommandLineRunner initUserDatabase(UserDTORepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new UserDTO("Pepe", "nombre calle", "pepe@go.com")));
			log.info("Preloading " + repository.save(new UserDTO("Juan", "nombre calle 2", "juan@go.com")));
		};
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages/messages");
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}
}
