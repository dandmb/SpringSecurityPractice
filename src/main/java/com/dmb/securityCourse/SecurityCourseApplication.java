package com.dmb.securityCourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
public class SecurityCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityCourseApplication.class, args);
	}

}
