package de.jxson.jutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class JutilsApplication {

	public static final String APPLICATION_SECRET_KEY = "JUTILS-?!ENCR-PASS_$§(%/)/§(HIDDEN";

	public static void main(String[] args) {
		SpringApplication.run(JutilsApplication.class, args);
	}

}
