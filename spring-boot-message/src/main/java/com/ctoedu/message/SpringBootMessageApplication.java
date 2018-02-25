package com.ctoedu.message;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ctoedu.message.mapper")
public class SpringBootMessageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMessageApplication.class, args);
	}
}
