package com.salesmanagementsystem.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "com.salesmanagementsystem")
@EntityScan("com.salesmanagementsystem.core.model")
@EnableJpaRepositories(basePackages = "com.salesmanagementsystem.core.data.repository")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication 
@EnableJpaAuditing
public class SalesManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesManagementSystemApplication.class, args);
	}

}
