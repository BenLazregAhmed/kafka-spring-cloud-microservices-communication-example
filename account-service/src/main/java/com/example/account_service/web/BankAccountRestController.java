package com.example.account_service.web;

import com.example.account_service.entities.BankAccount;
import com.example.account_service.repositories.BankAccountRepo;
import com.example.account_service.services.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import summer.camp.dtos.BankAccountDto;

import java.util.List;

@RestController
@AllArgsConstructor
public class BankAccountRestController {
    BankAccountRepo bankAccountRepo;
    AccountService accountService;
    @GetMapping(path = "/accounts")
    public List<BankAccount>accounts()
    {
        List<BankAccount>accounts=bankAccountRepo.findAll();
        /*accounts.forEach(
                bankAccount -> {
                    bankAccount.setCustomer(customerRestClient.findCustomerById(bankAccount.getCustomerId()));
                }
        );*/
        return accounts;
    }
    @GetMapping(path = "/account/{id}")

    public BankAccount getAccountById(@PathVariable String id)
    {
        BankAccount bankAccount=bankAccountRepo.findById(id).orElseThrow(()->new RuntimeException("NO BANK ACCOUNT"));
        return bankAccount;
    }

    @PostMapping(path = "/saveAccount")
    public void newAccount(@RequestBody BankAccountDto bankAccountDto)
    {
        accountService.saveAccount(bankAccountDto);
    }

}
