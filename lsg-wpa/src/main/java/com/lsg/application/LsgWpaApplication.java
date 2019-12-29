package com.lsg.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//{"com.lsg.wpa.controller","com.lsg.wpa.service"}
@SpringBootApplication
@ComponentScan(basePackages = {"com.lsg.advice","com.lsg.controller","com.lsg.service"})
@MapperScan("com.lsg.mapper")

public class LsgWpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LsgWpaApplication.class, args);
	}

}
