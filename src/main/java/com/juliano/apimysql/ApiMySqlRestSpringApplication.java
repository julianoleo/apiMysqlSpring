package com.juliano.apimysql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiMySqlRestSpringApplication {

	private static Logger log = LoggerFactory.getLogger(ApiMySqlRestSpringApplication.class);

	public static void main(String[] args) {
		log.info("Iniciando a API teste Mysql Spring Boot REST");
		SpringApplication.run(ApiMySqlRestSpringApplication.class, args);
		log.info("Api Iniciada");
	}
}
