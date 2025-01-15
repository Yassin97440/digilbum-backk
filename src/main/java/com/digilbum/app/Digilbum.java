package com.digilbum.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Digilbum {
	private static final Logger logger = LoggerFactory.getLogger(Digilbum.class);

	public static void main(String[] args) {
		logger.info("DATABASE_HOST: " + System.getenv("DATABASE_HOST"));
		logger.info("DATABASE_PORT: " + System.getenv("DATABASE_PORT"));
		logger.info("DATABASE_NAME: " + System.getenv("DATABASE_NAME"));
		logger.info("JDBC URL will be: jdbc:mysql://" + System.getenv("DATABASE_HOST") + ":"
				+ System.getenv("DATABASE_PORT") + "/" + System.getenv("DATABASE_NAME"));
		SpringApplication.run(Digilbum.class, args);
	}

}
