package com.workshop.main;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import com.workshop.main.model.TsscAdmin;
import com.workshop.main.services.TsscAdminService;

@SpringBootApplication
public class WorkshopTestNgApplication {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}


	public static void main(String[] args) {
		SpringApplication.run(WorkshopTestNgApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TsscAdminService adminService) {
		return (args) -> {
			

			TsscAdmin user1 = new TsscAdmin();
			user1.setUsername("admin");
			user1.setPassword("{noop}123");
			user1.setSuperAdmin("admin");
			adminService.save(user1);

			TsscAdmin user2 = new TsscAdmin();
			user2.setUsername("superadmin");
			user2.setPassword("{noop}123");
			user2.setSuperAdmin("superadmin");
			adminService.save(user2);


		};

	}

}
