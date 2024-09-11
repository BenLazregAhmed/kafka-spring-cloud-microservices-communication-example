package com.example.customer_service;

import com.example.customer_service.entities.Customer;
import com.example.customer_service.repositories.CustomersRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CustomersRepo customersRepo)
	{
		return args -> {
			Customer customer1=Customer.builder()
					.firstName("ahmed")
					.lastName("blz")
					.email("ahmed@gmail.com")
					.build();
			Customer customer2=Customer.builder()
					.firstName("sabiha")
					.lastName("mrabet")
					.email("sabiha@gmail.com")
					.build();
			Customer customer3=Customer.builder()
					.firstName("kamel")
					.lastName("blz")
					.email("kamel@gmail.com")
					.build();
			customersRepo.save(customer1);
			customersRepo.save(customer2);
			customersRepo.save(customer3);
		};
	}
}
