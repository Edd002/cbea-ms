package com.pucminas.cbea;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@Log
@EnableCaching
@ServletComponentScan
@SpringBootApplication(exclude = RepositoryRestMvcAutoConfiguration.class)
public class CBEAApplication {

	/*
	H2 URL: http://localhost:8080/cbea/h2-console
	Driver Class: org.h2.Driver
	JDBC URL: jdbc:h2:mem:db
	User Name: sa
	Password:

	Queries:
	select * from t_championship;
	select * from t_coach;
	select * from t_proof;
	select * from t_swimming_style;
	select * from t_proof_swimming_style;
	select * from t_team;
	select * from t_proof_team;
	select * from t_record;
	select * from t_swimmer;


	Open-API Swagger URL: http://localhost:8080/cbea/swagger-ui/index.html
	*/
	public static void main(String[] args) {
		SpringApplication.run(CBEAApplication.class, args);
	}
}