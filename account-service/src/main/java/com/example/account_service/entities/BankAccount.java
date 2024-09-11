package com.example.account_service.entities;
import com.example.account_service.models.Customer;
import jakarta.persistence.*;
import lombok.*;
import summer.camp.enums.AccountType;

import java.time.LocalDate;
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
@Builder
@Entity
public class BankAccount {
    @Id
    private String accountId;
    private double solde;
    private LocalDate createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @ManyToOne
    private Customer customer;
    private Long myCustomerId;
}
