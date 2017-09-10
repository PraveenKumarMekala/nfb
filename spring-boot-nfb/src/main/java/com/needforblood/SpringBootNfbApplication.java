package com.needforblood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.needforblood","com.needforblood.dynamodb.controller" })
public class SpringBootNfbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootNfbApplication.class, args);
	}
}
