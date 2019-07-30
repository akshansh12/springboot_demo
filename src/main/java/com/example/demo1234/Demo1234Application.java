package com.example.demo1234;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@EnableJpaRepositories(basePackages = "com.example.demo1234.repository")
@SpringBootApplication
public class Demo1234Application {



	public static void main(String[] args) {

	   ApplicationContext context= SpringApplication.run(Demo1234Application.class, args);
	}

}
