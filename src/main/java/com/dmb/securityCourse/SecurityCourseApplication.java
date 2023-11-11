package com.dmb.securityCourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
@SpringBootApplication
public class SecurityCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityCourseApplication.class, args);
	}

}
