package com.example.account_service.services;

import com.example.account_service.entities.BankAccount;
import com.example.account_service.models.Customer;
import com.example.account_service.repositories.BankAccountRepo;
import com.example.account_service.repositories.CustomerRepo;
import com.example.account_service.web.BankAccountRestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import summer.camp.dtos.BankAccountDto;
import summer.camp.enums.CustomerAccountStatus;
import summer.camp.events.AccountCorrespondingCustomerEvent;
import summer.camp.events.BankAccountCreatedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AccountService {
    private BankAccountRepo bankAccountRepo;
    private StreamBridge streamBridge;
    private CustomerRepo customerRepo;

    public void saveAccount(BankAccountDto bankAccount)
    {

        BankAccount account = bankAccountRepo.save(BankAccount.builder()
                .accountId(UUID.randomUUID().toString())
                .type(bankAccount.getType())
                .currency(bankAccount.getCurrency())
                .solde(bankAccount.getSolde())
                .myCustomerId(bankAccount.getCustomerId())
                .build());
        log.info("********************************");
        log.info("AccountCorrespondingCustomerEvent emitted");
        streamBridge.send("bankAccount", new BankAccountCreatedEvent(account.getMyCustomerId(), account.getAccountId()));
    }
    @Bean
    public Consumer<AccountCorrespondingCustomerEvent> customerConsumer(){
        return (input)->{
            log.info("********************************");
            log.info("AccountCorrespondingCustomerEvent received");
            BankAccount bankAccount = bankAccountRepo.findById(input.getAccountId()).orElse(null);
            if (bankAccount==null)
            {
                //do not throw exceptions in this function
                log.info("Account Not Found !!!");
                return;
            }
            log.info(input.getAccountId()+" STATUS==> "+input.getStatus());
            if (input.getStatus().equals(CustomerAccountStatus.SUCCEEDED)) {
                Customer customer = Customer.builder()
                        .id(input.getId())
                        .email(input.getEmail())
                        .lastName(input.getLastName())
                        .firstName(input.getFirstName())
                        .build();
                List<BankAccount> bankAccounts = new ArrayList<>();
                bankAccounts.add(bankAccount);
                customer.setAccounts(bankAccounts);
                log.info("********************************");
                log.info(customer.getEmail() + "==>" + customer.getFirstName() + "==>");
                customerRepo.save(customer);
                bankAccount.setCustomer(customer);
                bankAccountRepo.save(bankAccount);
            }
            else {
                bankAccountRepo.delete(bankAccount);
                log.error("Customer not Found !!!");
            }
        };
    }

}
