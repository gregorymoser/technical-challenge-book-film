package com.gm.demobook;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DemoBookApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoBookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}

}
