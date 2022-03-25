package com.jobbook.site;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages="com.jobbook.site.*")
public class JobBookApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(JobBookApplication.class, args);
	}

}
