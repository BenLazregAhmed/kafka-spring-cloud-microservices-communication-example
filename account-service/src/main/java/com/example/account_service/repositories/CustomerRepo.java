package com.example.account_service.repositories;

import com.example.account_service.entities.BankAccount;
import com.example.account_service.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
