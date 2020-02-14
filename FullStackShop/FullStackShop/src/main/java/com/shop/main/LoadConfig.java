package com.shop.main;

import lombok.extern.slf4j.Slf4j;

import com.shop.repositories.EmployeeRepository;
import com.shop.repositories.UserRepository;

import com.shop.entities.Employee;
import com.shop.entities.User;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.shop.repositories")
@EntityScan("com.shop.entities")
@ComponentScan(basePackages = { "com.shop.rest" })
@Configuration
@Slf4j
class LoadConfiguration {

	@Bean
	CommandLineRunner initEmployeeDatabase(EmployeeRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
		};
	}

	@Bean
	CommandLineRunner initUserDatabase(UserRepository repository) {
		return args -> {
			log.info("Preloading " + repository.save(new User("Pepe", "nombre calle", "pepe@go.com")));
			log.info("Preloading " + repository.save(new User("Juan", "nombre calle 2", "juan@go.com")));
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
