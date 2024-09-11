package com.example.customer_service.repositories;

import com.example.customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomersRepo extends JpaRepository<Customer,Long> {
}
