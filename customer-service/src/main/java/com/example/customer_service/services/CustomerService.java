package com.example.customer_service.services;

import com.example.customer_service.entities.Customer;
import com.example.customer_service.repositories.CustomersRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import summer.camp.enums.CustomerAccountStatus;
import summer.camp.events.AccountCorrespondingCustomerEvent;
import summer.camp.events.BankAccountCreatedEvent;

import java.util.function.Consumer;
import java.util.function.Function;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {
    private CustomersRepo customersRepo;
    private StreamBridge streamBridge;

    //@Bean
    public Consumer<BankAccountCreatedEvent> BankAccountCreatedEventConsumer(){
        return (input)->{
            System.out.println("***********Account Id received***********");
            System.out.println(input.getAccountId());
            System.out.println("**********************");
        };
    }
    @Bean
    public Function<BankAccountCreatedEvent,AccountCorrespondingCustomerEvent> bankAccountCreatedEventConsumer(){
        return (input)->{
            log.info("***************************");
            log.info("BankAccountCreatedEvent received");
            Customer customer=customersRepo.findById(input.getCustomerId()).orElse(null);
            log.info("*************************************");
            log.info("AccountCorrespondingCustomerEvent sent");
            if (customer!=null) {
                log.info("*************SUCCESS***********");
                return new AccountCorrespondingCustomerEvent(
                        input.getAccountId(),
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail(),
                        CustomerAccountStatus.SUCCEEDED)
                        ;
            }

            else {
                log.info("*************FAILURE***********");
                return new AccountCorrespondingCustomerEvent(input.getAccountId(),CustomerAccountStatus.FAILED);
            }
        };
    }

}
